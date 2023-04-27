package org.springframework.demo2.factory;

import org.springframework.demo2.entity.BeanDefinition;
import org.springframework.demo2.exception.BeansException;

public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;

    void registryBean(BeanDefinition beanDefinition);
}