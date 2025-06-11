package com.zhang.springframework.beans;

/**
 * @Auther: zhangyian
 * @Date: 2025/6/11 20:10
 * @Description: 定义属性
 */
public class PropertyValue {

    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }


}
