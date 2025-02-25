package com.lile.springframework.test;

import com.lile.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.lile.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.lile.springframework.context.support.ClassPathXmlApplicationContext;
import com.lile.springframework.test.bean.UserService07;
import com.lile.springframework.test.common.MyBeanFactoryPostProcessor;
import com.lile.springframework.test.common.MyBeanPostProcessor;
import org.junit.Test;

public class ApiTest07 {
    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring07.xml");

        // 3. BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化之后，修改 Bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. 获取Bean对象调用方法
        UserService07 userService = beanFactory.getBean("userService07", UserService07.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring07_enhance.xml");

        // 2. 获取Bean对象调用方法
        UserService07 userService = applicationContext.getBean("userService07", UserService07.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
}
