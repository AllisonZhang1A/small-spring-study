package com.zhang.springframework.beans.factory.support;

import com.zhang.springframework.beans.factory.config.BeanDefinition;

/**
 * @Auther: zhangyian
 * @Date: 2025/5/22 14:13
 * @Description:
 */
public interface BeanDefinitionRegistry {

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
