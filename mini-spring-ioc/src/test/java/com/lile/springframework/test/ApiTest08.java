package com.lile.springframework.test;

import com.lile.springframework.context.support.ClassPathXmlApplicationContext;
import com.lile.springframework.test.bean.UserService08;
import org.junit.Test;

public class ApiTest08 {
    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring08.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService08 userService = applicationContext.getBean("userService08", UserService08.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_hook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("close！")));
    }
}
