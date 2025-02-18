package com.lile.springframework.beans.factory;

import com.lile.springframework.beans.factory.aware.Aware;

public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);

}

