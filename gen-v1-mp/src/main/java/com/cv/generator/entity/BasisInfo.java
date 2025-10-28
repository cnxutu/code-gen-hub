/**
 * Copyright © 2019 dream horse Info. Tech Ltd. All rights reserved.
 *
 * @Package: com.github.mybatis.fl.entity
 * @author: flying-cattle
 * @date: 2019年4月9日 下午8:15:25
 */
package com.cv.generator.entity;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.cv.generator.config.ApplicationConfig;
import com.cv.generator.util.MySqlToJavaUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Copyright: Copyright (c) 2019
 *
 * <p>说明： 自动生成需要的基本信息</P>
 *
 * @version: v3.0.0
 * @author: flying-cattle
 * <p>
 * Modification History:
 * Date         	Author          Version          Description
 * ---------------------------------------------------------------*
 * 2019年4月9日      		flying-cattle   v3.0.0           initialize
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasisInfo implements Serializable {
    private static final long serialVersionUID = 123123L;

    private String project;

    private String author;

    private String version;

    private String dbUrl;

    private String dbName;

    private String dbPassword;

    private String database;

    private String table;

    private String entityName;

    private String objectName;

    private String entityComment;

    private String createTime;

    private String agile;

    private String serialVersionId;

    private String entityUrl;

    private String queryUrl;

    private String paramUrl;

    private String convertUrl;

    private String dtoUrl;

    private String voUrl;

    private String daoUrl;

    private String mapperUrl;

    private String serviceUrl;

    private String serviceImplUrl;

    private String abstractControllerUrl;

    private String controllerUrl;

    private String swaggerConfigUrl;

    private String idType;

    private String idJdbcType;

    private List<PropertyInfo> cis;

    private String isSwagger = "false";

    private String isSl4j = "true";

    private String entityExtendsClassPackage;

    private String entityExtendsClassName;

    private String mapperExtendsClassPackage;

    private String mapperExtendsClassName;

    private String serviceExtendsClassPackage;

    private String serviceExtendsClassName;

    private String serviceImplExtendsClassPackage;

    private String serviceImplExtendsClassName;

    // 框架包
    private String frameworkPackage;

    private String frameworkErrorCodeEnum;

    private String frameworkDeletedEnum;

    private String frameworkDeleteByIdListQuery;

    private String frameworkBizException;

    private String frameworkResult;

    private String frameworkPageQuery;

    private String frameworkPageInfoVO;

    private String frameworkPageUtils;

    // 忽略字段

    private String ignoreColumns;

    private String ignorePrefix = "";

    private String addSuffix = "";

    private Set<String> pkgs = new HashSet<>();

    public BasisInfo(String project, String author, String version, String dbUrl, String dbName, String dbPassword,
                     String database, String createTime, String entityUrl, String queryUrl, String daoUrl, String mapperUrl,
                     String serviceUrl, String serviceImplUrl, String controllerUrl, String isSwagger) {
        super();
        this.project = project;
        this.author = author;
        this.version = version;
        this.dbUrl = dbUrl;
        this.dbName = dbName;
        this.dbPassword = dbPassword;
        this.database = database;
        this.createTime = createTime;
        this.agile = System.currentTimeMillis() + "";
        this.entityUrl = entityUrl;
        this.queryUrl = queryUrl;
        this.daoUrl = daoUrl;
        this.mapperUrl = mapperUrl;
        this.serviceUrl = serviceUrl;
        this.serviceImplUrl = serviceImplUrl;
        this.controllerUrl = controllerUrl;
        this.abstractControllerUrl = controllerUrl.substring(0, controllerUrl.lastIndexOf(".")) + ".aid";
        this.swaggerConfigUrl = controllerUrl.substring(0, controllerUrl.lastIndexOf(".")) + ".config";
        this.isSwagger = isSwagger;
    }

    public BasisInfo(ApplicationConfig applicationConfig) {
        super();
        this.project = applicationConfig.getProject();
        this.author = applicationConfig.getAuthor();
        this.version = applicationConfig.getVersion();
        this.dbUrl = applicationConfig.getUrl();
        this.dbName = applicationConfig.getUsername();
        this.dbPassword = applicationConfig.getPassword();
        this.database = dbUrl.substring(dbUrl.lastIndexOf("/") + 1, dbUrl.lastIndexOf("?"));
        this.ignorePrefix = applicationConfig.getIgnorePrefix();
        this.ignoreColumns = getIgnoreColumns(applicationConfig.getIgnoreColumns());
        this.createTime = DateUtil.now();
        this.entityExtendsClassPackage = applicationConfig.getEntityExtendsClassPackage();
        this.entityExtendsClassName = applicationConfig.getEntityExtendsClassName();
        this.mapperExtendsClassPackage = applicationConfig.getMapperExtendsClassPackage();
        this.mapperExtendsClassName = applicationConfig.getMapperExtendsClassName();
        this.serviceExtendsClassPackage = applicationConfig.getServiceExtendsClassPackage();
        this.serviceExtendsClassName = applicationConfig.getServiceExtendsClassName();
        this.serviceImplExtendsClassPackage = applicationConfig.getServiceImplExtendsClassPackage();
        this.serviceImplExtendsClassName = applicationConfig.getServiceImplExtendsClassName();
        this.entityUrl = applicationConfig.getEntity();
        this.queryUrl = applicationConfig.getQuery();
        this.paramUrl = applicationConfig.getParam();
        this.convertUrl = applicationConfig.getConvert();
        this.voUrl = applicationConfig.getVo();
        this.dtoUrl = applicationConfig.getDto();
        this.daoUrl = applicationConfig.getMapper();
        this.mapperUrl = applicationConfig.getXml();
        this.serviceUrl = applicationConfig.getService();
        this.serviceImplUrl = applicationConfig.getServiceImpl();
        this.controllerUrl = applicationConfig.getController();
        this.addSuffix = applicationConfig.getAddSuffix();
        this.isSl4j = applicationConfig.getIsSl4j();
//        this.abstractControllerUrl = controllerUrl.substring(0, controllerUrl.lastIndexOf("."))+".aid";
//        this.swaggerConfigUrl=controllerUrl.substring(0, controllerUrl.lastIndexOf("."))+".config";
        this.isSwagger = applicationConfig.getIsSwagger();

        // 框架包
        this.frameworkPackage = applicationConfig.getFrameworkPackage();
        this.frameworkErrorCodeEnum = applicationConfig.getFrameworkErrorCode();
        this.frameworkDeletedEnum = applicationConfig.getFrameworkDeletedEnum();
        this.frameworkDeleteByIdListQuery = applicationConfig.getFrameworkDeleteByIdListQuery();
        this.frameworkBizException = applicationConfig.getFrameworkBizException();
        this.frameworkResult = applicationConfig.getFrameworkResult();
        this.frameworkPageQuery = applicationConfig.getFrameworkPageQuery();
        this.frameworkPageInfoVO = applicationConfig.getFrameworkPageInfoVO();
        this.frameworkPageUtils = applicationConfig.getFrameworkPageUtils();
    }

    private String getIgnoreColumns(String ignoreColumns) {
        if (StrUtil.isNotBlank(ignoreColumns)) {
            StringBuilder result = new StringBuilder();
            for (String column : ignoreColumns.split(",")) {
                String newColumn = MySqlToJavaUtil.changeToJavaFiled(column, getIgnorePrefix());
                result.append(newColumn).append(",");
            }
            return result.substring(0, result.lastIndexOf(","));
        }
        return ignoreColumns;
    }


}
