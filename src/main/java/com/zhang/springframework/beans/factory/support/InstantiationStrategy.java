package com.zhang.springframework.beans.factory.support;

import com.zhang.springframework.beans.BeansException;
import com.zhang.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Auther: zhangyian
 * @Date: 2025/5/22 19:58
 * @Description:定义实例化策略接口：定义了如何实例化bean的策略
 * 1.提供统一的实例化方法
 * 2.提供实例化的策略，通过策略模式
 * 3.支持构造函数参数
 */
public interface InstantiationStrategy {
    /**
     * 在实例化接口 instantiate 方法中添加必要的入参信息，包括：beanDefinition、 beanName、ctor、args
     * @param beanDefinition
     * @param beanName
     * @param ctor 它是 java.lang.reflect 包下的 Constructor 类，里面包含了一些必要的类信息，有这个参数的目的就是为了拿到符合入参信息相对应的构造函数。
     * @param args 就是一个具体的入参信息了，最终实例化时候会用到
     * @return
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
