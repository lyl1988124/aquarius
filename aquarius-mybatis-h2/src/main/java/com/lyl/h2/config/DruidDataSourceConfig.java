package com.lyl.h2.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author lyl
 * @Package com.lyl.h2.config
 * @Title: DruidDataSourceConfig
 * @Description:
 * @date 2020/6/23 21:35
 */
@Configuration
public class DruidDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource dataSource() {
        System.out.println("####注入druid！！！");

        DataSource datasource = new DruidDataSource();

        return datasource;
    }
}
