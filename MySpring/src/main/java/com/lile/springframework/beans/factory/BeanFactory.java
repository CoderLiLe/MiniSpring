package com.lile.springframework.beans.factory;

import com.lile.springframework.beans.BeansException;
import com.lile.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
