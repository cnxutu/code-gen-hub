package com.cv;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.StrUtil;
import com.cv.generator.config.ApplicationConfig;
import com.cv.generator.config.SystemConfig;
import com.cv.generator.entity.BasisInfo;
import com.cv.generator.util.EntityInfoUtil;
import com.cv.generator.util.Generator;
import com.cv.generator.util.MySqlToJavaUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@SpringBootTest
public class CodeGeneratorApplicationTests {

    @Resource
    private ApplicationConfig applicationConfig;

    @Resource
    private SystemConfig systemConfig;  // 新增注入

    @Test
    public void contextLoads() {
        TimeInterval timer = DateUtil.timer();
        String tableNames = applicationConfig.getTableNames();
        if (StrUtil.isBlank(tableNames)) {
            return;
        }
        tableNames = tableNames.replace("，", ",");
        String[] tableNameArr = tableNames.split(",");
        BasisInfo bi = new BasisInfo(applicationConfig);
        String fileUrl = systemConfig.getOutputDir();// 生成文件存放位置
        for (String tableName : tableNameArr) {
            bi.setTable(tableName);
            bi.setSerialVersionId(new Date().getTime() + "");
            bi.setEntityName(MySqlToJavaUtil.getClassName(tableName, applicationConfig.getIgnorePrefix()));
            bi.setObjectName(MySqlToJavaUtil.changeToJavaFiled(tableName, applicationConfig.getIgnorePrefix()));
            bi.setEntityComment(EntityInfoUtil.getTableComment(bi));
            try {
                bi = EntityInfoUtil.getInfo(bi);
                //开始生成文件
                String aa1 = Generator.createEntity(fileUrl, bi).toString();
                String aa2 = Generator.createDao(fileUrl, bi).toString();
                String aa3 = Generator.createDaoImpl(fileUrl, bi).toString();
                String aa4 = Generator.createService(fileUrl, bi).toString();
                String aa5 = Generator.createServiceImpl(fileUrl, bi).toString();
                String aa6 = Generator.createController(fileUrl, bi).toString();
                String aa7 = Generator.createAddParam(fileUrl, bi).toString();
                String aa9 = Generator.createPageQuery(fileUrl, bi).toString();
                String aa10 = Generator.createPageVO(fileUrl, bi).toString();
                String aa11 = Generator.createVO(fileUrl, bi).toString();
                String aa12 = Generator.createDto(fileUrl, bi).toString();
                String aa13 = Generator.createConvert(fileUrl, bi).toString();
                // 是否创建swagger配置文件
//                String aa7 = Generator.createSwaggerConfig(fileUrl, bi).toString();

                System.out.println(aa1);
                System.out.println(aa2);
                System.out.println(aa3);
                System.out.println(aa4);
                System.out.println(aa5);
                System.out.println(aa6);
                System.out.println(aa7);
                System.out.println(aa8);
                System.out.println(aa9);
                System.out.println(aa10);
                System.out.println(aa11);
                System.out.println(aa12);
                System.out.println(aa13);

                //System.out.println(aa7);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            String os = systemConfig.getOs().toLowerCase();
            if ("windows".equals(os)) {
                Runtime.getRuntime().exec(new String[]{
                        "cmd", "/c", "start", "\"\"", "explorer", "\"" + fileUrl + "\""
                });
            } else if ("linux".equals(os)) {
                Runtime.getRuntime().exec("xdg-open " + fileUrl);
            } else if ("mac".equals(os)) {
                Runtime.getRuntime().exec("open " + fileUrl);
            } else {
                System.out.println("⚠️ 未知系统类型: " + os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\r\n");
        System.out.println("\r\n");
        System.out.println("共执行 " + timer.intervalRestart() + " ms");
        System.out.println("\r\n");
        System.out.println("\r\n");
    }


}
