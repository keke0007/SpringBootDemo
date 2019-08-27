package com.kejiang.springboot_servlet.controller;

import com.kejiang.springboot_servlet.pojo.User;
import com.kejiang.springboot_servlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ Author：ke
 * @ Date： 2019/7/28 16:51
 * @ Version 1.0
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public List<User> getUsers() {

        return userService.getUsers();
    }
}
