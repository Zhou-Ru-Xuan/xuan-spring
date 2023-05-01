package org.springframeworkdemo.demo8.processor;

import org.springframeworkdemo.demo8.exception.BeansException;

public interface BeanPostProcessor {
    /**
     * 用于Bean初始化之前
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 用于Bean初始化之后
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}