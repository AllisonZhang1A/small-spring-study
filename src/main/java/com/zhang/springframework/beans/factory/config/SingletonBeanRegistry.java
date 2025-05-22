package com.zhang.springframework.beans.factory.config;

/**
 * @Auther: zhangyian
 * @Date: 2025/5/22 14:12
 * @Description: 单例注册接口定义和实现
 * Spring默认Bean是单例的，所以需要有一个“单例池”来存放已经创建好的Bean对象。
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例对象
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);
}
