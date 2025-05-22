package com.zhang.springframework.beans;

/**
 * @Auther: zhangyian
 * @Date: 2025/5/22 14:14
 * @Description:
 */
public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
