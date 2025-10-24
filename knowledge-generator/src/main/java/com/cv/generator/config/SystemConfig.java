package com.cv.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: xutu
 * @since: 2025/9/1 10:53
 */
@Data
@Component
@ConfigurationProperties(prefix = "system")
public class SystemConfig {
    private String os; // windows / linux / mac

    private String outputDir;
}