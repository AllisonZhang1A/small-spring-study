package com.zhang.springframework.beans.factory.support;

import com.zhang.springframework.beans.BeansException;
import com.zhang.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @Auther: zhangyian
 * @Date: 2025/5/22 20:09
 * @Description:
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    /**
     * 通过Cglib来创建有构造函数的bean
     * @param beanDefinition
     * @param beanName
     * @param ctor 它是 java.lang.reflect 包下的 Constructor 类，里面包含了一些必要的类信息，有这个参数的目的就是为了拿到符合入参信息相对应的构造函数。
     * @param args 就是一个具体的入参信息了，最终实例化时候会用到
     * @return
     * @throws BeansException
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        //1.创建Enhancer对象，用于生成代理对象
        Enhancer enhancer=new Enhancer();
        //2.设置要代理的目标类
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        //3.设置回调函数
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        //4.根据是否有构造函数，选择不同的方式创建对象
        if(null==ctor){
            return enhancer.create();
        }
        //如果有构造函数，则使用指定构造函数创建
        return enhancer.create(ctor.getParameterTypes(),args);
    }
}
