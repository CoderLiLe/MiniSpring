package com.lile.springframework.test;

import com.lile.springframework.beans.factory.config.BeanDefinition;
import com.lile.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.lile.springframework.test.bean.UserService04;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;

public class ApiTest04 {
    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService04.class);
        beanFactory.registerBeanDefinition("UserService04", beanDefinition);

        // 3.获取bean
        UserService04 UserService04 = (UserService04) beanFactory.getBean("UserService04", "李四");
        UserService04.queryUserInfo();
    }

    @Test
    public void test_newInstance() throws IllegalAccessException, InstantiationException {
        UserService04 UserService04 = UserService04.class.newInstance();
        System.out.println(UserService04);
    }

    @Test
    public void test_constructor() throws Exception {
        Class<UserService04> UserService04Class = UserService04.class;
        Constructor<UserService04> declaredConstructor = UserService04Class.getDeclaredConstructor(String.class);
        UserService04 UserService04 = declaredConstructor.newInstance("李四");
        System.out.println(UserService04);
    }

    @Test
    public void test_parameterTypes() throws Exception {
        Class<UserService04> beanClass = UserService04.class;
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        Constructor<?> constructor = declaredConstructors[0];
        Constructor<UserService04> declaredConstructor = beanClass.getDeclaredConstructor(constructor.getParameterTypes());
        UserService04 UserService04 = declaredConstructor.newInstance("李四");
        System.out.println(UserService04);
    }

    @Test
    public void test_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService04.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object obj = enhancer.create(new Class[]{String.class}, new Object[]{"李四"});
        System.out.println(obj);
    }
}
