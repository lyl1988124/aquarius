package com.lyl.aquarius.shiro.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyl_pc
 */
@Api(tags ={"生产者进程API接口"})
@RestController
@RequestMapping("/producer")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value="发送解析文本", notes="发送解析文本", produces="application/json")
    @RequestMapping(value="/sendText", method= RequestMethod.POST)
    public String sendText(@RequestBody String text) {
        logger.info("发送的文本内容：{}", text);

        return text;
    }
}
