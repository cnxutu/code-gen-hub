package com.cv.generator.config;

import cn.hutool.core.util.StrUtil;
import com.cv.generator.util.MySqlToJavaUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.Resource;

/**
 * @author xutu
 * @Title: code-generator
 * @Package com.generator.entity
 * @date 2019/12/12 21:21
 */
@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "global-config")
@Data
public class ApplicationConfig {

    @Resource
    private DataSourceConfig dataSourceConfig;

    @Resource
    private PackageConfig packageConfig;

    @Resource
    private FrameworkPathConfig frameworkPathConfig;


    private String project;

    private String author;

    private String version;

    private String isSwagger;

    private String isSl4j;

    private String outputDir;

    private String url;

    private String username;

    private String password;

    private String tableNames;

    private String ignoreColumns;

    private String parent;

    private String controller;

    private String service;

    private String serviceImpl;

    private String mapper;

    private String entity;

    private String query;

    private String param;

    private String dto;

    private String convert;

    private String vo;

    private String xml;

    private String entityExtendsClassPackage;

    private String entityExtendsClassName;

    private String mapperExtendsClassPackage;

    private String mapperExtendsClassName;

    private String serviceExtendsClassPackage;

    private String serviceExtendsClassName;

    private String serviceImplExtendsClassPackage;

    private String serviceImplExtendsClassName;

    private String ignorePrefix;

    private String addSuffix;

    // 框架包
    private String frameworkPackage;

    private String frameworkErrorCode;

    private String frameworkDeletedEnum;

    private String frameworkDeleteByIdListQuery;

    private String frameworkBizException;

    private String frameworkResult;

    private String frameworkPageQuery;

    private String frameworkPageInfoVO;

    private String frameworkPageUtils;

    public String getFrameworkPackage() {
        return frameworkPathConfig.getParentPackage();
    }

    public String getFrameworkErrorCode() {
        return frameworkPathConfig.getErrorCode();
    }

    public String getFrameworkDeletedEnum() {
        return frameworkPathConfig.getDeletedEnum();
    }

    public String getFrameworkDeleteByIdListQuery() {
        return frameworkPathConfig.getDeleteIdsQuery();
    }

    public String getFrameworkBizException() {
        return frameworkPathConfig.getBizException();
    }

    public String getFrameworkResult() {
        return frameworkPathConfig.getResult();
    }

    public String getFrameworkPageQuery() {
        return frameworkPathConfig.getPageQuery();
    }

    public String getFrameworkPageInfoVO() {
        return frameworkPathConfig.getPageInfo();
    }

    public String getFrameworkPageUtils() {
        return frameworkPathConfig.getPageUtils();
    }


    public String getUrl() {
        return dataSourceConfig.getUrl();
    }

    public String getUsername() {
        return dataSourceConfig.getUsername();
    }

    public String getPassword() {
        return dataSourceConfig.getPassword();
    }

    public String getTableNames() {
        return dataSourceConfig.getTableNames();
    }

    public String getIgnorePrefix() {
        return packageConfig.getIgnorePrefix();
    }

    public String getAddSuffix() {
        return packageConfig.getAddSuffix();
    }

    public String getIgnoreColumns() {
        if(StrUtil.isNotBlank(dataSourceConfig.getIgnoreColumns())){
            StringBuilder result= new StringBuilder();
            for (String column : dataSourceConfig.getIgnoreColumns().split(",")) {
                String newColumn = MySqlToJavaUtil.changeToJavaFiled(column,ignorePrefix);
                result.append(newColumn).append(",");
            }
            return result.substring(0,result.lastIndexOf(","));
        }
        return dataSourceConfig.getIgnoreColumns();
    }

    public String getParent() {
        return packageConfig.getParent();
    }

    public String getController() {
        return packageConfig.getController();
    }

    public String getService() {
        return packageConfig.getService();
    }

    public String getServiceImpl() {
        return packageConfig.getServiceImpl();
    }

    public String getMapper() {
        return packageConfig.getMapper();
    }

    public String getEntity() {
        return packageConfig.getEntity();
    }

    public String getQuery() {
        return packageConfig.getQuery();
    }
    public String getDto() {
        return packageConfig.getDto();
    }

    public String getParam() {
        return packageConfig.getParam();
    }

    public String getConvert() {
        return packageConfig.getConvert();
    }

    public String getVo() {
        return packageConfig.getVo();
    }

    public String getXml() {
        return packageConfig.getXml();
    }

    public String getEntityExtendsClassPackage() {
        return packageConfig.getEntityExtendsClassPackage();
    }

    public String getEntityExtendsClassName() {
        return packageConfig.getEntityExtendsClassName();
    }

    public String getMapperExtendsClassPackage() {
        return packageConfig.getMapperExtendsClassPackage();
    }

    public String getMapperExtendsClassName() {
        return packageConfig.getMapperExtendsClassName();
    }

    public String getServiceExtendsClassPackage() {
        return packageConfig.getServiceExtendsClassPackage();
    }

    public String getServiceExtendsClassName() {
        return packageConfig.getServiceExtendsClassName();
    }

    public String getServiceImplExtendsClassPackage() {
        return packageConfig.getServiceImplExtendsClassPackage();
    }

    public String getServiceImplExtendsClassName() {
        return packageConfig.getServiceImplExtendsClassName();
    }
}
