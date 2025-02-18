package com.lile.springframework.beans.factory;

import com.lile.springframework.beans.factory.aware.Aware;

public interface BeanNameAware extends Aware {

    void setBeanName(String name);

}

