package com.lile.springframework.beans.factory.support;

import com.lile.springframework.beans.factory.FactoryBean;
import com.lile.springframework.test.bean.IUser;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.InvocationHandler;
import java.util.HashMap;
import java.util.Map;

public class ProxyBeanFactory2 implements FactoryBean<IUser> {

    @Override
    public IUser getObject() throws Exception {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("10001", "李四");
        hashMap.put("10002", "张三");
        hashMap.put("10003", "王五");

        InvocationHandler handler = (proxy, method, args) -> {
            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
        };

        return new ByteBuddy()
                .subclass(IUser.class)
                .method(ElementMatchers.any())
                .intercept(InvocationHandlerAdapter.of(handler))
                .make()
                .load(IUser.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
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
