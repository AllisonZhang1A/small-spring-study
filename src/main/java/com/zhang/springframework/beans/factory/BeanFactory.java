package com.zhang.springframework.beans.factory;

import com.zhang.springframework.beans.BeansException;
import com.zhang.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: zhangyian
 * @Date: 2025/5/22 13:39
 * @Description:
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    /**
     * 重载了一个含有入参信息 args 的 getBean 方法，这样就可以方便的传递入参给构造函数实例化了。
     * @param beanName
     * @param args
     * @return
     * @throws BeansException
     */
    Object getBean(String beanName,Object...args) throws BeansException;

}
