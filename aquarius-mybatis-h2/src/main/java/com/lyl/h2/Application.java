package com.lyl.h2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author lyl
 * @Package com.lyl.h2
 * @Title: Application
 * @Description:
 * @date 2020/6/23 19:50
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lyl.h2"})
@EnableSwagger2
@MapperScan("com.lyl.h2.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.run();
    }
}
