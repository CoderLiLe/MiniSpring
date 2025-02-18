package com.lile.springframework.beans.factory;

import com.lile.springframework.beans.BeansException;
import com.lile.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.lile.springframework.beans.factory.config.BeanDefinition;
import com.lile.springframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;
}
