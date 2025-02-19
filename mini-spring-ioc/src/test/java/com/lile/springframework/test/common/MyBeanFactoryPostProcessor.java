package com.lile.springframework.test.common;

import com.lile.springframework.beans.BeansException;
import com.lile.springframework.beans.PropertyValue;
import com.lile.springframework.beans.PropertyValues;
import com.lile.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.lile.springframework.beans.factory.config.BeanDefinition;
import com.lile.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService07");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}
