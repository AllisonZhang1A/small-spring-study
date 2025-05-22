package com.zhang.springframework.beans.factory.support;

import com.zhang.springframework.beans.BeansException;
import com.zhang.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Auther: zhangyian
 * @Date: 2025/5/22 14:13
 * @Description:
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    //定义了一个创建对象的实例化策略属性类 InstantiationStrategy instantiationStrategy，这里我们选择了 Cglib 的实现类。
    private InstantiationStrategy instantiationStrategy=new CglibSubclassingInstantiationStrategy();

    /**
     * 实现了 Bean 的实例化操作
     * @param beanName bean的名字
     * @param beanDefinition bean的定义
     * @return
     * @throws BeansException
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws BeansException {
        Object bean = null;
        try{
            bean = createBeanInstance(beanDefinition,beanName,args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        //创建好后加入单例池子
        addSingleton(beanName,bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args){
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        //循环比对出构造函数集合与入参信息 args 的匹配情况
        for(Constructor constructor : declaredConstructors){
            if(null!=args&&constructor.getParameterTypes().length==args.length){
                constructorToUse=constructor;
                break;
            }
        }
        //通过策略调用
        Object instantiate = instantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
        return instantiate;
    }
}
