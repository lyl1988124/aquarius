package com.lyl.aquarius.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @author lyl_pc
 */
@Api(value ="用户API接口")
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value="发送解析文本", notes="发送解析文本")
    @RequestMapping(value="/sendText", method= RequestMethod.GET)
    public String sendText(@RequestParam String text) {
        logger.info("发送的文本内容：{}", text);

        return text;
    }

    @ApiOperation(value="用户登录", notes="发送解析文本")
    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String login(
        @ApiParam(value = "login info", required = false)
        @Nullable String text,
        @Nullable HttpServletResponse response) {
        logger.info("发送的文本内容：{}", text);

        response.addCookie(new Cookie("testCookie","cookieValue"));

        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken("account", "password");
            subject.login(usernamePasswordToken);

            Serializable tokenId = subject.getSession().getId();

            subject.login(usernamePasswordToken);

            return tokenId.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    @RequiresRoles(value = {"user"})
    @ApiOperation(value="查找用户信息", notes="查找用户信息")
    @RequestMapping(value="/{userId}", method= RequestMethod.GET)
    @ResponseBody
    public String getUser(
        @ApiParam(value = "param userId")
        @PathVariable(value = "userId")String userId) {
        logger.info("查找用户信息：{}", userId);



        try {
            return "userInfo = "+userId;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    @ApiOperation(value="登出", notes="登出")
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ResponseBody
    public void afterLogout(@Nullable String text) {
        //这里一定要使用shiro退出方式，否则session失效
        logger.info("logout：{}", text);
        SecurityUtils.getSubject().logout();
    }
}
