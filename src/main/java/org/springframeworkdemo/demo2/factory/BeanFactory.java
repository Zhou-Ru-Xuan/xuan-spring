package org.springframeworkdemo.demo2.factory;

import org.springframeworkdemo.demo2.entity.BeanDefinition;
import org.springframeworkdemo.demo2.exception.BeansException;

public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;

    void registryBean(BeanDefinition beanDefinition);
}