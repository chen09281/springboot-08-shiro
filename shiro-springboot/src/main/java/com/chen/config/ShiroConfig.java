package com.chen.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    // ShiroFilterFactoryBean:3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        // 添加shiro的内置过滤器
        /*
            anno: 无需认证就可以访问
            authc: 必须认证了才能访问
            user: 必须拥有 记住我功能才能用
            perms: 拥有对某个资源的权限才能访问
            role: 拥有某个角色权限，才能访问
        * */
        Map<String, String> filterMap = new LinkedHashMap<>();
        /* filterMap.put("/user/add","authc");
        filterMap.put("/user/update","authc");*/
        filterMap.put("/user/*","authc");

        bean.setFilterChainDefinitionMap(filterMap);
        // 设置登录的请求
        bean.setLoginUrl("/toLogin");
        return bean;
    }

    @Bean(name = "securityManager")
    // DefaultWebSecurityManager:2
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联UserRealm
        securityManager.setRealm(userRealm);

        return securityManager;
    }

    // 创建realm 对象 ,需要自定义:1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
