package com.shiro.service;

import com.shiro.domain.User;
import com.shiro.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author：ke
 * @ Date： 2019/6/17 10:31
 * @ Version 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
