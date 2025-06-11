package com.zhang.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhangyian
 * @Date: 2025/6/11 20:10
 * @Description:因为属性可能会有很多，所以还需要定义一个集合包装下
 */
public class PropertyValues {

    /**属性列表**/
    private final List<PropertyValue> propertyValues=new ArrayList<>();

    /**
     * 添加属性
     * @param propertyValue
     */
    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValues.add(propertyValue);
    }

    /**
     * 获取属性
     * @return
     */
    public PropertyValue[] getPropertyValues() {
        return this.propertyValues.toArray(new PropertyValue[0]);
    }

    /**
     * 根据属性名获取属性
     * @param propertyName 属性名
     * @return
     */
    public PropertyValue getPropertyValue(String propertyName) {
        //遍历属性列表
        for(PropertyValue pv:this.propertyValues) {
            //对比属性名
            if(pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }
}
