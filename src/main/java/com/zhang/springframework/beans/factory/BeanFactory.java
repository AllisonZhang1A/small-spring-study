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

}
