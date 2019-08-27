package com.shiro.domain;

import lombok.Data;

/**
 * @ Author：ke
 * @ Date： 2019/6/17 10:21
 * @ Version 1.0
 */
@Data
public class User {
    private int id;
    private String name;
    private String password;
    private String perms;
}
