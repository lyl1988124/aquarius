package com.lyl.h2.controller;

import com.lyl.h2.entity.User;
import com.lyl.h2.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

/**
 * @author lyl
 * @Package com.lyl.h2.controller
 * @Title: UserController
 * @Description:
 * @date 2020/6/23 20:14
 */

@Api(value ="用户API接口")
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value="createUser", notes="创建用户")
    @RequestMapping(value="/create", method= RequestMethod.POST)
    public String createUser(@RequestBody User user) {
        logger.info("发送的文本内容：{}", user);

        userService.insertUser(user);

        return user.toString();
    }

    @ApiOperation(value="getUser", notes="获取用户")
    @RequestMapping(value="/user/{id}", method= RequestMethod.POST)
    public String getUser(
        @ApiParam("user id")
        @PathVariable("id") Long id) {
        logger.info("发送的文本内容：{}", id);

        User user = userService.getUser(id);

        return user.toString();
    }
}


