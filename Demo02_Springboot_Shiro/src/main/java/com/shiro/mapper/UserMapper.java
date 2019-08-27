package com.shiro.mapper;

import com.shiro.domain.User;
import org.apache.ibatis.annotations.Select;

/**
 * @ Author：ke
 * @ Date： 2019/6/17 10:26
 * @ Version 1.0
 */

public interface UserMapper {

    @Select("select id,name,password,perms from user where name=#{name} ")
    public User findByName(String name);
    @Select("select id,name,password,perms from user where id=#{id} ")
    public User findById(Integer id);
}
