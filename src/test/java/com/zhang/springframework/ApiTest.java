package com.zhang.springframework;

import com.zhang.springframework.beans.UserService;
import com.zhang.springframework.beans.factory.config.BeanDefinition;
import com.zhang.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @Auther: zhangyian
 * @Date: 2025/5/22 13:46
 * @Description:
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "小傅哥");
        userService.queryUserInfo();
    }
}
