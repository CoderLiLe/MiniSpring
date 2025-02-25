package com.lile.springframework.test;

import com.lile.springframework.beans.PropertyValues;
import com.lile.springframework.beans.factory.config.BeanDefinition;
import com.lile.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.lile.springframework.test.bean.UserService05;
import com.lile.springframework.test.bean.UserDao;
import com.lile.springframework.beans.PropertyValue;
import com.lile.springframework.beans.factory.config.BeanReference;

import org.junit.Test;

public class ApiTest05 {
    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService05.class, propertyValues);
        beanFactory.registerBeanDefinition("userService05", beanDefinition);

        // 5. UserService 获取bean
        UserService05 userService05 = (UserService05) beanFactory.getBean("userService05");
        userService05.queryUserInfo();
    }
}
