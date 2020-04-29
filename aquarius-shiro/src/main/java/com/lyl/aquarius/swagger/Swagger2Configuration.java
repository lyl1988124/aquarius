package com.lyl.aquarius.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author lyl_pc
 */
@Configuration
public class Swagger2Configuration  {
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.lyl.aquarius.shiro.controller";
    public static final String VERSION = "1.0.0";
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("aquarius service")
                .description("aquarius API 接口文档")
                .version(VERSION)
                .license("aquarius license")
                .licenseUrl("http://www.baidu.com")
                .build();
    }
}

