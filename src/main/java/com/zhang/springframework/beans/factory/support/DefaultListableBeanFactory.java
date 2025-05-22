package com.zhang.springframework.beans.factory.support;

import com.zhang.springframework.beans.BeansException;
import com.zhang.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhangyian
 * @Date: 2025/5/22 14:13
 * @Description:核心实现类
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{

    private Map<String,BeanDefinition> beanDefinitionMap=new HashMap<>();



    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition=beanDefinitionMap.get(beanName);
        if(beanDefinition==null){
            throw new BeansException("No bean named"+beanName+"is defined");
        }
        return beanDefinition;
    }


    /**
     * bean的注册
     * @param beanName bean名字
     * @param beanDefinition bean的定义
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
