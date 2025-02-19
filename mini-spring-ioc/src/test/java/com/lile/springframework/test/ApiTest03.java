package com.lile.springframework.test;

import com.lile.springframework.beans.factory.config.BeanDefinition;
import com.lile.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.lile.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest03 {

    @Test
    public void testBeanFactory03() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4.第二次获取bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }
}
