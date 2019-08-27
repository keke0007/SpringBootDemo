package com.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @ Author：ke
 * @ Date： 2019/6/16 20:59
 * @ Version 1.0
 */
@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("add", "测试");
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update(Model model) {
        model.addAttribute("add", "测试");
        return "/user/update";
    }
    @RequestMapping("/toLogin")
    public String login(Model model) {
        model.addAttribute("add", "测试");
        return "/login";
    }

    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model) {
        //把数据放入model
        model.addAttribute("name","黑马Program");
        //返回test.html
        return "/login";
    }
    @RequestMapping("/noAuth")
    public String noAuth(Model model) {
        //把数据放入model
        model.addAttribute("name","黑马Program");
        //返回test.html
        return "/noAuth";
    }

    @RequestMapping("/login")
    public String logins(String name,String password,Model model) {
        System.out.println(name);
        /**
         * 使用shiro编写认证逻辑
         */
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        //3.执行登录方法
        try {
            subject.login(token);
            //表示登录成功
            //跳转到test,html
            return "test";
        } catch (UnknownAccountException e) {
            //登录失败,用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            //登录失败,密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }
}
