package com.cv.analyzer.core;

import com.cv.analyzer.pojo.dto.MethodInfoDTO;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.Type;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author: xutu
 * @since: 2025/9/1 20:19
 */
public class CodeAnalyzer {
    private static final String ROOT_FILE_PATH = "/Users/richtu/data/workspace/coding/tu-test";
    private static final String SRC_KEY = "src/main/java";

    public static void main(String[] args) throws Exception {
        // 1、文件绝对路径地址
        String implPath = ROOT_FILE_PATH + SRC_KEY
                + "/com/cqjd/project/service/impl/CarnieSysMenuServiceImpl.java";

        // 2、可添加制定方案解析
        // 可选：指定需要解析的方法名
        // ===== 在这里直接写死你想解析的方法名称 =====
        Set<String> targetMethods = new HashSet<>();
        targetMethods.add("add");
//        targetMethods.add("edit");
//        targetMethods.add("delete"); // 根据需要继续添加
        // ========================================


        // 3、执行目标 类/方法 代码解析程序
        codeAnalyze(implPath, targetMethods);
    }


    private static void codeAnalyze(String implPath, Set<String> targetMethods) throws IOException {
        String code = new String(Files.readAllBytes(Paths.get(implPath)));
        CompilationUnit cu = StaticJavaParser.parse(code);

        List<MethodInfoDTO> methods = new ArrayList<>();

        cu.findAll(MethodDeclaration.class).forEach(method -> {
            String methodName = method.getNameAsString();
            if (!targetMethods.isEmpty() && !targetMethods.contains(methodName)) {
                return; // 跳过不在名单中的方法
            }

            MethodInfoDTO info = new MethodInfoDTO();
            info.setMethodName(methodName);
            info.setReturnType(method.getTypeAsString());
            method.getComment().ifPresent(c -> info.setComment(c.getContent().trim()));

            List<MethodInfoDTO.Parameter> params = new ArrayList<>();
            method.getParameters().forEach(p -> {
                MethodInfoDTO.Parameter param = new MethodInfoDTO.Parameter();
                param.setName(p.getNameAsString());
                param.setType(p.getTypeAsString());
                param.setFields(parseFieldsByType(p.getType(), cu, implPath));
                params.add(param);
            });
            info.setParameters(params);
            methods.add(info);
        });

        String json = new Gson().toJson(methods);
        Files.write(Paths.get("method-info.json"), json.getBytes());
        System.out.println("解析完成，生成 method-info.json");
    }


    private static String findSrcRootFromImplPath(String implPath) {
        int idx = implPath.indexOf(SRC_KEY);
        if (idx != -1) {
            return implPath.substring(0, idx + SRC_KEY.length());
        }
        return null;
    }

    private static List<MethodInfoDTO.FieldInfo> parseFieldsByType(Type type, CompilationUnit currentCU, String implPath) {
        List<MethodInfoDTO.FieldInfo> fields = new ArrayList<>();
        try {
            String typeName = type.asString();
            String srcRoot = findSrcRootFromImplPath(implPath);
            if (srcRoot == null) {
                return fields;
            }

            // 优先从 import 找全路径
            String fullClass = currentCU.getImports().stream()
                    .map(i -> i.getNameAsString())
                    .filter(i -> i.endsWith("." + typeName))
                    .findFirst()
                    .orElse(null);

            String filePath;
            if (fullClass != null) {
                filePath = srcRoot + "/" + fullClass.replace('.', '/') + ".java";
            } else {
                // 没有 import 就认为是同包
                String packageName = currentCU.getPackageDeclaration()
                        .map(p -> p.getNameAsString())
                        .orElse("");
                filePath = srcRoot + "/" + packageName.replace('.', '/') + "/" + typeName + ".java";
            }

            File file = new File(filePath);
            if (!file.exists()) {
                return fields;
            }

            String code = new String(Files.readAllBytes(file.toPath()));
            CompilationUnit cu = StaticJavaParser.parse(code);

            cu.findFirst(ClassOrInterfaceDeclaration.class).ifPresent(clazz -> {
                for (FieldDeclaration field : clazz.getFields()) {
                    field.getVariables().forEach(var -> {
                        MethodInfoDTO.FieldInfo fi = new MethodInfoDTO.FieldInfo();
                        fi.setName(var.getNameAsString());
                        fi.setType(field.getElementType().asString());
                        field.getComment().ifPresent(c -> fi.setComment(c.getContent().trim()));
                        fields.add(fi);
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fields;
    }

}