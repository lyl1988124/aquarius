package com.lyl.h2.service;

import com.lyl.h2.entity.User;
import com.lyl.h2.mapper.UserMapper;
import org.h2.tools.Server;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @author lyl
 * @Package com.lyl.h2.service
 * @Title: UserServiceTest
 * @Description:
 * @date 2020/6/26 2:13
 */

@RunWith(SpringRunner.class)
@ActiveProfiles({"test"})
@SpringBootTest(classes = {
    DataSourceAutoConfiguration.class,
    MybatisAutoConfiguration.class,
    UserService.class,
    H2ConsoleAutoConfiguration.class,
    ApplicationContext.class
}
,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@ComponentScan(basePackages = {"com.lyl.h2"})
@MapperScan("com.lyl.h2.mapper")
//@WebAppConfiguration
public class UserServiceTest {
    @Resource
    UserService userService;


//    @Before
//    public void initTest() throws SQLException {
//        Server server = Server.createWebServer("-web",
//            "-webAllowOthers", "-webPort", "8080");
//        server.start();
//        System.out.println("启动成功：" + server.getStatus());
//    }

    @Test
    @Transactional
    public void testUserService() {
        User user = new User();
        user.setPassWord("123");
        user.setRealName("daguoguo");
        user.setUserName("lyl");
        userService.insertUser(user);
        System.out.println("#####");
        System.out.println(userService.getUser(1L));
        System.out.println(userService.getUser(5L));
    }
}
