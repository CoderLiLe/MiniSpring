package com.lile.springframework.beans.factory;

import com.lile.springframework.beans.BeansException;
import com.lile.springframework.beans.factory.aware.Aware;

public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}

