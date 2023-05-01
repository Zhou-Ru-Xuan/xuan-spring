package org.springframeworkdemo.demo3.factory;

import org.springframeworkdemo.demo3.entity.BeanDefinition;
import org.springframeworkdemo.demo3.exception.BeansException;

public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;

    Boolean containsBean(String name);

    void registerBean(String beanName, BeanDefinition obj);
}