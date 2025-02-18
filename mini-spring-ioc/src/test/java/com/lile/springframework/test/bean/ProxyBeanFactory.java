package com.lile.springframework.test.bean;

import com.lile.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProxyBeanFactory implements FactoryBean<IUser> {

    @Override
    public IUser getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "李四");
            hashMap.put("10002", "张三");
            hashMap.put("10003", "王五");
            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
        };
        return (IUser) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUser.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUser.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
