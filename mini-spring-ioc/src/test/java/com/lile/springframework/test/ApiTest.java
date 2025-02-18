package com.lile.springframework.test;

import com.lile.springframework.beans.factory.config.BeanDefinition;
import com.lile.springframework.beans.factory.BeanFactory;
import com.lile.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.lile.springframework.test.bean.UserService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;

public class ApiTest {

    @Test
    public void testBeanFactory02() {
        // 1.初始化 BeanFactory
//        BeanFactory beanFactory = new BeanFactory();
//
//        // 2.注册bean
//        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
//        beanFactory.registerBeanDefinition("userService", beanDefinition);
//
//        // 3.获取bean
//        UserService userService = (UserService) beanFactory.getBean("userService");
//        userService.queryUserInfo();
    }

    @Test
    public void testBeanFactory03() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4.第二次获取bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }

    @Test
    public void testBeanFactory04() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "李四");
        userService.queryUserInfo();
    }

    @Test
    public void test_newInstance() throws IllegalAccessException, InstantiationException {
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
    }

    @Test
    public void test_constructor() throws Exception {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = declaredConstructor.newInstance("李四");
        System.out.println(userService);
    }

    @Test
    public void test_parameterTypes() throws Exception {
        Class<UserService> beanClass = UserService.class;
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        Constructor<?> constructor = declaredConstructors[0];
        Constructor<UserService> declaredConstructor = beanClass.getDeclaredConstructor(constructor.getParameterTypes());
        UserService userService = declaredConstructor.newInstance("李四");
        System.out.println(userService);
    }

    @Test
    public void test_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
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
