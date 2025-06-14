package com.zhang.springframework;

import cn.hutool.core.io.IoUtil;
import com.zhang.springframework.beans.PropertyValue;
import com.zhang.springframework.beans.PropertyValues;
import com.zhang.springframework.beans.UserDao;
import com.zhang.springframework.beans.UserService;
import com.zhang.springframework.beans.factory.config.BeanDefinition;
import com.zhang.springframework.beans.factory.config.BeanReference;
import com.zhang.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

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

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }


}
