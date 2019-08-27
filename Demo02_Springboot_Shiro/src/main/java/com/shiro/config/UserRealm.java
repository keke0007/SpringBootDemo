package com.shiro.config;

/**
 * @ Author：ke
 * @ Date： 2019/6/16 21:18
 * @ Version 1.0
 */

import com.shiro.domain.User;
import com.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 认证授权类
 */
public class UserRealm extends AuthorizingRealm{
    @Autowired
    private UserService userService;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");
        //从数据库查询当前登录用户的授权字符串
        Subject subject = SecurityUtils.getSubject();
       User user =(User) subject.getPrincipal();

        User dbUser = userService.findById(user.getId());
        info.addStringPermission(dbUser.getPerms());
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //假设数据库的用户名和密码
       // String name = "eric";
        //String password = "123456";
        //编写shiro判断逻辑,判断用户名和密码

        //1.判断用户名
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        User user = userService.findByName(token.getUsername());
        if (user == null) {
            //用户名不存在
            return null;//shiro底层会抛出UnknowAccoutException
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
