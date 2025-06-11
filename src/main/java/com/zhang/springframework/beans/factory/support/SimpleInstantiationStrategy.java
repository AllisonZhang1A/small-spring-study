package com.zhang.springframework.beans.factory.support;

import com.zhang.springframework.beans.BeansException;
import com.zhang.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Auther: zhangyian
 * @Date: 2025/5/22 20:00
 * @Description:JDK实例化策略实现，利用JDK的反射机制来实例化bean
 * 1.使用Java反射创建对象，通过JDK的反射机制来实例化bean
 * 2.如果bean有构造函数，就使用构造函数来实例化bean
 * 3.如果bean没有构造函数，就使用无参构造函数来实例化b
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        //首先通过 beanDefinition 获取 Class 信息，这个 Class 信息是在 Bean 定义的时候传递进去的。
        Class clazz = beanDefinition.getBeanClass();
        try{
            
            if(null != ctor){
                //1.如果有构造函数，就使用构造函数来实例化bean.把入参信息传递给 newInstance 进行实例化。
                //getDeclaredConstructor() 方法可以获取到 Class 中所有的构造函数，
                //ctor.getParameterTypes() 方法可以获取到构造函数的参数类型，
                //newInstance() 方法可以实例化对象。
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }else{
                //2.如果没有构造函数，就使用无参构造函数来实例化bean
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
