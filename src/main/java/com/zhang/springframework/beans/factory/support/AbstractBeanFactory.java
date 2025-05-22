package com.zhang.springframework.beans.factory.support;

import com.zhang.springframework.beans.BeansException;
import com.zhang.springframework.beans.factory.BeanFactory;
import com.zhang.springframework.beans.factory.config.BeanDefinition;

/**
 * @Auther: zhangyian
 * @Date: 2025/5/22 14:13
 * @Description: 模版方法的核心，负责定义整个getBean流程的骨架。AbstractBeanFactory继承DefaultSingletonBeanRegistry，就是为了让Bean工厂拥有“单例池”功能
 * 但具体“如何创建 Bean 实例”这件事，可能有多种实现方式（比如有的支持自动注入，有的支持构造函数注入等），所以这部分逻辑就用抽象方法，让子类去实现。
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName) throws BeansException{
        Object bean = getSingleton(beanName);//如果单例池里有就取出来
        if(bean != null){
            return bean;
        }
        //如果单例池子里没有，那就说明这个 bean 还没有被创建过
        // 这时就需要去“注册表”（通常是 beanDefinitionMap）中查找 bean 的定义信息（BeanDefinition），然后根据定义信息创建 bean 实例。
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        //创建bean
        Object newBean = createBean(beanName, beanDefinition);
        return newBean;
    }

    /**
     * 获取bean的定义
     * @param beanName
     * @return bean的定义
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 根据bean的定义创建bean
     * @param beanName bean的名字
     * @param beanDefinition bean的定义
     * @return
     * @throws BeansException
     */
    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition) throws BeansException;
}
