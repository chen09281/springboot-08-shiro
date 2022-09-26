package com.chen.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

// 自定义的UserRealm    extends AuthorizingRealm
public class UserRealm extends AuthorizingRealm {
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");

        // 用户名，密码 数据库中取
        String name = "root";
        String password = "chen781+-/";

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        if (!userToken.getUsername().equals(name)){
            return null; // 抛出异常 UnknownAccountException
        }

        // 密码认证, shiro做
        return new SimpleAuthenticationInfo("",password,"");
    }
}
