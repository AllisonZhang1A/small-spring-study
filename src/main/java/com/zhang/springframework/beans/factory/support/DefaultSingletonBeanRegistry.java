package com.zhang.springframework.beans.factory.support;

import com.zhang.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhangyian
 * @Date: 2025/5/22 14:13
 * @Description:
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**用来存放已经创建好的单例Bean**/
    private Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 获取单例bean。多次通过getBean获取同一个bean的时候Spring会直接从单例池中返回已经创建好的对象
     * @param beanName
     * @return 单例bean
     */
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 添加单例对象
     * @param beanName
     * @param singletonObject
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
