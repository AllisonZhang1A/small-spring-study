package com.zhang.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.zhang.springframework.beans.BeansException;
import com.zhang.springframework.beans.PropertyValue;
import com.zhang.springframework.beans.PropertyValues;
import com.zhang.springframework.beans.factory.config.BeanDefinition;
import com.zhang.springframework.beans.factory.config.BeanReference;

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
            //创造bean
            bean = createBeanInstance(beanDefinition,beanName,args);
            //给bean填充属性
            applyPropertyValues(beanName,bean,beanDefinition);

        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        //创建好后加入单例池子
        addSingleton(beanName,bean);
        return bean;
    }

    /**
     * 创造bean
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
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
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * bean属性填充
     * @param beanName bean名字
     * @param bean
     * @param beanDefinition bean的定义
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            //从注册中获取属性值
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // A 依赖 B，获取 B 的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName, e);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }


}
