package com.kejiang.springboot_servlet.service.impl;

import com.kejiang.springboot_servlet.mapper.UserMapper;
import com.kejiang.springboot_servlet.pojo.User;
import com.kejiang.springboot_servlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author：ke
 * @ Date： 2019/7/28 16:48
 * @ Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getUsers() {
        return userMapper.selectUsers();
    }
}
