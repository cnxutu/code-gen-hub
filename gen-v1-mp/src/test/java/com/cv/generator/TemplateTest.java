package com.cv.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * @author: xutu
 * @since: 2025/10/28 16:33
 */
@SpringBootTest
public class TemplateTest {


    /**
     * 测试模板语法
     */
    @Test
    void testTemplateSyntax() throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/freemarker/ftl"));
        cfg.setDefaultEncoding("UTF-8");

        Template t = cfg.getTemplate("mapper.ftl");
        System.out.println("模板加载成功 ✅");
    }

}
