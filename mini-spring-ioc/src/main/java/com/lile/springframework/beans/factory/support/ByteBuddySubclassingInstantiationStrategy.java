package com.lile.springframework.beans.factory.support;

import com.lile.springframework.beans.BeansException;
import com.lile.springframework.beans.factory.config.BeanDefinition;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;

import java.lang.reflect.Constructor;

public class ByteBuddySubclassingInstantiationStrategy implements InstantiationStrategy {

    /**
     * 根据Bean定义和构造函数参数实例化Bean对象
     *
     * @param beanDefinition Bean定义，包含Bean类和其他元数据
     * @param beanName Bean名称
     * @param ctor 构造函数对象，用于实例化Bean
     * @param args 构造函数参数
     * @return 实例化的Bean对象
     * @throws BeansException 当实例化失败时抛出此异常
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        try {
            // 使用ByteBuddy动态生成子类
            DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                    .subclass(beanDefinition.getBeanClass())
                    .make();

            // 加载动态生成的类
            Class<?> dynamicTypeClass = dynamicType.load(beanDefinition.getBeanClass().getClassLoader()).getLoaded();

            // 根据构造函数实例化Bean
            if (ctor == null) {
                return dynamicTypeClass.getDeclaredConstructor().newInstance();
            } else {
                return dynamicTypeClass.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }
        } catch (Exception e) {
            // 如果实例化失败，抛出BeansException异常
            throw new BeansException("Failed to instantiate bean", e);
        }
    }
}
