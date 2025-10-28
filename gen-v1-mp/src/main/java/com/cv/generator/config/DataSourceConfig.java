package com.cv.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author xutu
 * @Title: code-generator
 * @Package com.generator.config
 * @date 2019/12/12 17:41
 */
@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "global-config.datasource")
@Data
public class DataSourceConfig {

    private String url;

    private String username;

    private String password;

    private String tableNames;

    private String ignoreColumns;

}
