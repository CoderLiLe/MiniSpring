package com.lile.springframework.test.common;

import com.lile.springframework.beans.BeansException;
import com.lile.springframework.beans.factory.config.BeanPostProcessor;
import com.lile.springframework.test.bean.UserService07;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService07".equals(beanName)) {
            UserService07 userService = (UserService07) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
