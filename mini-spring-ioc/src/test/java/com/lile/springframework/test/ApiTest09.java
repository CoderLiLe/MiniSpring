package com.lile.springframework.test;

import com.lile.springframework.context.support.ClassPathXmlApplicationContext;
import com.lile.springframework.test.bean.UserService09;
import org.junit.Test;

public class ApiTest09 {
    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring09.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService09 userService = applicationContext.getBean("userService", UserService09.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);

        System.out.println("ApplicationContextAware：" + userService.getApplicationContext());
        System.out.println("BeanFactoryAware：" + userService.getBeanFactory());
    }
}
