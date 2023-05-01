package org.springframework.beans;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
