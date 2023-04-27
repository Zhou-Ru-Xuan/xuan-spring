package org.springframework.demo4.factory;


import org.springframework.demo4.exception.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;

    boolean containsBean(String name);

    boolean isSingleton(String name);

    boolean isPrototype(String name);

    Class<?> getType(String name);
}