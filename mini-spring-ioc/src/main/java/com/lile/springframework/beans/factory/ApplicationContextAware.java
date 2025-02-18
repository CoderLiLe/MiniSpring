package com.lile.springframework.beans.factory;

import com.lile.springframework.beans.BeansException;
import com.lile.springframework.beans.factory.aware.Aware;
import com.lile.springframework.context.ApplicationContext;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}

