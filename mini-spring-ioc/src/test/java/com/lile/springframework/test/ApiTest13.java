package com.lile.springframework.test;

import com.lile.springframework.aop.AdvisedSupport;
import com.lile.springframework.aop.ClassFilter;
import com.lile.springframework.aop.MethodMatcher;
import com.lile.springframework.aop.TargetSource;
import com.lile.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.lile.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.lile.springframework.aop.framework.ProxyFactory;
import com.lile.springframework.aop.framework.ReflectiveMethodInvocation;
import com.lile.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.lile.springframework.context.support.ClassPathXmlApplicationContext;
import com.lile.springframework.test.bean.IUserService12;
import com.lile.springframework.test.bean.UserService12;
import com.lile.springframework.test.common.UserServiceBeforeAdvice;
import com.lile.springframework.test.common.UserServiceInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ApiTest13 {
    private AdvisedSupport advisedSupport;

    @Before
    public void init() {
        // 目标对象
        IUserService12 userService = new UserService12();
        // 组装代理信息
        advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.lile.springframework.test.bean.IUserService12.*(..))"));
    }

    @Test
    public void test_proxyFactory() {
        advisedSupport.setProxyTargetClass(false); // false/true，JDK动态代理、CGlib动态代理
        IUserService12 proxy = (IUserService12) new ProxyFactory(advisedSupport).getProxy();

        System.out.println("测试结果：" + proxy.queryUserInfo());
    }

    @Test
    public void test_beforeAdvice() {
        UserServiceBeforeAdvice beforeAdvice = new UserServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor interceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
        advisedSupport.setMethodInterceptor(interceptor);

        IUserService12 proxy = (IUserService12) new ProxyFactory(advisedSupport).getProxy();
        System.out.println("测试结果：" + proxy.queryUserInfo());
    }

    @Test
    public void test_advisor() {
        // 目标对象
        IUserService12 userService = new UserService12();

        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* com.lile.springframework.test.bean.IUserService12.*(..))");
        advisor.setAdvice(new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice()));

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(userService.getClass())) {
            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(userService);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(true); // false/true，JDK动态代理、CGlib动态代理

            IUserService12 proxy = (IUserService12) new ProxyFactory(advisedSupport).getProxy();
            System.out.println("测试结果：" + proxy.queryUserInfo());
        }
    }

    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring13.xml");

        IUserService12 userService = applicationContext.getBean("userService", IUserService12.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

    @Test
    public void test_proxy_method() {
        // 目标对象(可以替换成任何的目标对象)
        Object targetObj = new UserService12();

        // AOP 代理
        IUserService12 proxy = (IUserService12) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            // 方法匹配器
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.lile.springframework.test.bean.IUserService12.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObj.getClass())) {
                    // 方法拦截器
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };
                    // 反射调用
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                }
                return method.invoke(targetObj, args);
            }
        });

        String result = proxy.queryUserInfo();
        System.out.println("测试结果：" + result);

    }

}
