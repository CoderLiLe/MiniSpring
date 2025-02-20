package com.lile.springframework.test;

import com.lile.springframework.context.support.ClassPathXmlApplicationContext;
import com.lile.springframework.test.event.CustomEvent;
import org.junit.Test;

public class ApiTest11 {
    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring11.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 6666666888888L, "success!!!"));

        applicationContext.registerShutdownHook();
    }
}
