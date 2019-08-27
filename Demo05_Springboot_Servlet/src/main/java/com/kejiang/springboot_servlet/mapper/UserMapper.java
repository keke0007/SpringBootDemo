package com.kejiang.springboot_servlet.mapper;

import com.kejiang.springboot_servlet.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ Author：ke
 * @ Date： 2019/7/28 16:41
 * @ Version 1.0
 */
@Mapper
public interface UserMapper {
    @Select("select * from user")
    public List<User> selectUsers();
}
