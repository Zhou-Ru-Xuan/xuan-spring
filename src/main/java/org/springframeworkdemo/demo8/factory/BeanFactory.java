package org.springframeworkdemo.demo8.factory;

import org.springframeworkdemo.demo8.exception.BeansException;

public interface BeanFactory{
    Object getBean(String beanName) throws BeansException;

    Boolean containsBean(String name);

    boolean isSingleton(String name);

    boolean isPrototype(String name);

    Class<?> getType(String name);
}