package com.zhang.springframework.beans.factory.config;

/**
 * @Auther: zhangyian
 * @Date: 2025/6/11 20:09
 * @Description: bean的引用
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
