package com.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ Author：ke
 * @ Date： 2019/6/16 21:04
 * @ Version 1.0
 */
@SpringBootApplication
@MapperScan("com.shiro.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    /**
     * Shiro认证授权的逻辑是
     * 入口为subject-->用户主体--->把操作交给SecurityManager
     * SecurityManager-->安全管理器(关联Realm)
     * Realm: Shiro连接数据库的桥梁
     */
}
