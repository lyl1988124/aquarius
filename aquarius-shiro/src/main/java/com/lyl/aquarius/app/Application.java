package com.lyl.aquarius.app;

import org.apache.shiro.spring.boot.autoconfigure.ShiroAnnotationProcessorAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lyl_pc
 */
@SpringBootApplication(exclude = {ShiroAnnotationProcessorAutoConfiguration.class})
@ComponentScan(basePackages = {"com.lyl.aquarius"})
@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.run();
    }
}
