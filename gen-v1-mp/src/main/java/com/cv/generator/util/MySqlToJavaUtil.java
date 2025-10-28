/**
 * Copyright © 2019 dream horse Info. Tech Ltd. All rights reserved.
 *
 * @Package: com.github.mybatis.fl.util
 * @author: flying-cattle
 * @date: 2019年4月9日 下午8:15:25
 */
package com.cv.generator.util;


import cn.hutool.core.util.StrUtil;
import com.cv.generator.convert.DateType;
import com.cv.generator.convert.MySqlTypeConvert;

/**
 * Copyright: Copyright (c) 2019 
 *
 * <p>说明： 获奖java中需要的驼峰命名</P>
 * @version: v3.0.0
 * @author: flying-cattle
 *
 * Modification History:
 * Date         	Author          Version          Description
 *---------------------------------------------------------------*
 * 2019年4月9日      		flying-cattle   v3.0.0           initialize
 */
public class MySqlToJavaUtil {

    /**
     * <p>说明:获取java类名</p>
     * @param ignorePrefix 忽略前缀
     * @param table  表名
     * @return String
     */
    public static String getClassName(String table, String ignorePrefix) {
        if (StrUtil.isNotBlank(ignorePrefix) && table.contains(ignorePrefix)) {
            table = table.substring(table.indexOf(ignorePrefix) + ignorePrefix.length(), table.length());
        }
        table = changeToJavaFiled(table, ignorePrefix);
        StringBuilder sbuilder = new StringBuilder();
        char[] cs = table.toCharArray();
        cs[0] -= 32;
        sbuilder.append(String.valueOf(cs));
        return sbuilder.toString();
    }

    /**
     * <p>说明:获取字段名，把"_"后面字母变大写</p>
     * @param field  字段名
     * @return String
     */
    public static String changeToJavaFiled(String field, String ignorePrefix) {
        if (StrUtil.isNotBlank(ignorePrefix) && field.contains(ignorePrefix)) {
            field = field.substring(field.indexOf(ignorePrefix) + ignorePrefix.length(), field.length());
        }
        String[] fields = field.split("_");
        StringBuilder sbuilder = new StringBuilder(fields[0]);
        for (int i = 1; i < fields.length; i++) {
            char[] cs = fields[i].toCharArray();
            cs[0] -= 32;
            sbuilder.append(String.valueOf(cs));
        }
        return sbuilder.toString();
    }

    public static void main(String[] args) {
        String ignorePrefix = "tb_";
        String table = "tb_user";
        String className = getClassName(table, ignorePrefix);
        System.out.println(className);
        String changeToJavaFiled = changeToJavaFiled(table, ignorePrefix);
        System.out.println(changeToJavaFiled);
    }


    /**
     * <p>说明:把sql的数据类型转为java需要的类型</p>
     * @param sqlType  sql类型
     * @return String  java类型
     */
    public static String jdbcTypeToJavaType(String sqlType) {
        MySqlTypeConvert typeConvert = new MySqlTypeConvert();
        // 指定为 java8 新时间类
        return typeConvert.processTypeConvert(DateType.TIME_PACK, sqlType).getType();
    }

    /**
     * <p>说明:把sql的数据类型转为java需要的类型</p>
     * @param sqlType  sql类型
     * @return String  java类型
     */
    public static String jdbcTypeToJavaTypePck(String sqlType) {
        MySqlTypeConvert typeConvert = new MySqlTypeConvert();
        // 指定为 java8 新时间类
        return typeConvert.processTypeConvert(DateType.TIME_PACK, sqlType).getPkg();
    }
}
