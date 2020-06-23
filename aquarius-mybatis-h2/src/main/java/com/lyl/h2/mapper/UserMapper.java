package com.lyl.h2.mapper;

import com.lyl.h2.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author lyl
 * @Package com.lyl.h2.mapper
 * @Title: UserMapper
 * @Description:
 * @date 2020/6/23 20:11
 */
@Repository
public interface UserMapper {

    void insertUser(User user);

    User getUser(Long id);
}
