/**
 * Copyright © 2019 dream horse Info. Tech Ltd. All rights reserved.
 *
 * @Package: com.github.mybatis.fl.util
 * @author: flying-cattle
 * @date: 2019年4月9日 下午8:15:25
 */
package com.cv.generator.util;


import cn.hutool.core.util.StrUtil;
import com.cv.generator.entity.BasisInfo;
import com.cv.generator.entity.PropertyInfo;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Copyright: Copyright (c) 2019
 *
 * <p>说明：  链接数据库并获取表信息</P>
 *
 * @version: v3.0.0
 * @author: flying-cattle
 * <p>
 * Modification History:
 * Date         	Author          Version          Description
 * ---------------------------------------------------------------*
 * 2019年4月9日      		flying-cattle   v3.0.0           initialize
 */
public class EntityInfoUtil {

    private static Pattern REPLACE = Pattern.compile("\\s*|\t|\r|\n");

    public static BasisInfo getInfo(BasisInfo bi) throws SQLException {
        List<PropertyInfo> columns = new ArrayList<>();
        // 创建连接
        Connection con = null;
        PreparedStatement pstemt = null;
        ResultSet rs = null;
        //sql
        StringBuilder sb = new StringBuilder();
        sb.append("select ")
                .append("column_name, ").append("data_type, ").append("column_comment ");
        sb.append("from information_schema.columns where table_schema='").append(bi.getDatabase())
                .append("' and table_name = '").append(bi.getTable()).append("'");
        String sql = sb.toString();//"select column_name,data_type,column_comment from information_schema.columns where table_schema='"+bi.getDatabase()+"' and table_name='"+bi.getTable()+"'";
        try {
            con = DriverManager.getConnection(bi.getDbUrl(), bi.getDbName(), bi.getDbPassword());
            pstemt = con.prepareStatement(sql);
            rs = pstemt.executeQuery();
            while (rs.next()) {
                PropertyInfo ci = new PropertyInfo();
                String column = rs.getString(1);
                if (column.contains("_")) {
                    ci.setHump("true");
                } else {
                    ci.setHump("false");
                }
                String columnName = MySqlToJavaUtil.changeToJavaFiled(column, bi.getIgnorePrefix());
                String ignoreColumns = bi.getIgnoreColumns();
                if (StrUtil.isNotBlank(ignoreColumns)) {
                    if (Arrays.asList(ignoreColumns.split(",")).contains(columnName)) {
                        continue;
                    }
                }
                String jdbcType = rs.getString(2);
                String comment = rs.getString(3);

                ci.setColumn(column);
                if ("int".equalsIgnoreCase(jdbcType)) {
                    ci.setJdbcType("Integer");
                } else if ("datetime".equalsIgnoreCase(jdbcType)) {
                    ci.setJdbcType("timestamp");
                } else {
                    ci.setJdbcType(jdbcType);
                }
                ci.setComment(comment);
                ci.setProperty(columnName);
                ci.setJavaType(MySqlToJavaUtil.jdbcTypeToJavaType(jdbcType));
                //设置注解类型
                if ("id".equalsIgnoreCase(column)) {
                    bi.setIdType(ci.getJavaType());
                    bi.setIdJdbcType(ci.getJdbcType());
                }
                columns.add(ci);
                //添加包路径
                Set<String> pkgs = bi.getPkgs();
                pkgs.add(MySqlToJavaUtil.jdbcTypeToJavaTypePck(jdbcType));
                bi.setPkgs(pkgs);
            }
            bi.setCis(columns);
            // 完成后关闭
            rs.close();
            pstemt.close();
            con.close();
            if (CollectionUtils.isEmpty(columns)) {
                throw new RuntimeException("未能读取到表或表中的字段。请检查链接url，数据库账户，数据库密码，查询的数据名、是否正确。");
            }
            return bi;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("自动生成实体类错误：" + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException se2) {
            }
            // 关闭资源
            try {
                if (pstemt != null) pstemt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static String getTableComment(BasisInfo bi) {
        // 创建连接
        Connection con = null;
        PreparedStatement pstemt = null;
        ResultSet rs = null;
        //sql
        StringBuilder sb = new StringBuilder();
        sb.append("select ").append("table_comment ").append("from ").append("information_schema.tables ")
                .append("where table_schema='").append(bi.getDatabase())
                .append("' and table_name ='").append(bi.getTable()).append("'");
        String sql = sb.toString();
        String tableComment = "";
        try {
            con = DriverManager.getConnection(bi.getDbUrl(), bi.getDbName(), bi.getDbPassword());
            pstemt = con.prepareStatement(sql);
            rs = pstemt.executeQuery();
            while (rs.next()) {
                tableComment = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("自动生成实体类错误：" + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException se2) {
            }
            // 关闭资源
            try {
                if (pstemt != null) pstemt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return REPLACE.matcher(tableComment).replaceAll("");
    }

}
