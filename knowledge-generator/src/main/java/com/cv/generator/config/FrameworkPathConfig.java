package com.cv.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: xutu
 * @since: 2025/9/30 11:23
 */
@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "global-config.package-config.base-framework")
@Data
public class FrameworkPathConfig {

    private String parentPackage;

    private String errorCode;

    private String deletedEnum;

    private String deleteIdsQuery;

    private String bizException;

    private String result;

    private String pageQuery;

    private String pageInfo;

    private String pageUtils;

}
