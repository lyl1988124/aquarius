package com.lyl.h2.service;

import com.lyl.h2.entity.User;
import com.lyl.h2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lyl
 * @Package com.lyl.h2.service
 * @Title: UserService
 * @Description:
 * @date 2020/6/23 20:13
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void insertUser(User user){
        userMapper.insertUser(user);
    }

    public User getUser(Long id){
        return userMapper.getUser(id);
    }
}
