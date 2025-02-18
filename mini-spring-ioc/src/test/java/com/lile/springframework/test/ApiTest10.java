package com.lile.springframework.test;

import com.lile.springframework.context.support.ClassPathXmlApplicationContext;
import com.lile.springframework.test.bean.UserService10;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class ApiTest10 {
    @Test
    public void test_prototype() {
        // 设置系统属性以允许 JOL 获取 Instrumentation
        System.setProperty("jdk.attach.allowAttachSelf", "true");
        System.setProperty("jol.tryWithSudo", "true");
        System.setProperty("XX:+EnableDynamicAgentLoading", "");

        // 1. 初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring10.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService10 userService01 = applicationContext.getBean("userService", UserService10.class);
        UserService10 userService02 = applicationContext.getBean("userService", UserService10.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        // 4. 打印十六进制哈希
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());

    }

    @Test
    public void test_factory_bean() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring10.xml");
        applicationContext.registerShutdownHook();

        // 2. 调用代理方法
        UserService10 userService = applicationContext.getBean("userService", UserService10.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

}
