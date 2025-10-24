package com.cv.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author xutu
 * @Title: code-generator
 * @Package com.generator.config
 * @date 2019/12/12 17:43
 */
@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "global-config.package-config")
@Data
public class PackageConfig {

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

    public String getParent() {
        return parent + ".";
    }

    public String getController() {
        return getParent() + controller;
    }

    public String getService() {
        return getParent() + service;
    }

    public String getServiceImpl() {
        return getParent() + serviceImpl;
    }

    public String getMapper() {
        return getParent() + mapper;
    }

    public String getEntity() {
        return getParent() + entity;
    }

    public String getQuery() {
        return getParent() + query;
    }

    public String getParam() {
        return getParent() + param;
    }

    public String getDto() {
        return getParent() + dto;
    }

    public String getConvert() {
        return getParent() + convert;
    }

    public String getVo() {
        return getParent() + vo;
    }

    public String getXml() {
        return getParent() + xml;
    }


}
