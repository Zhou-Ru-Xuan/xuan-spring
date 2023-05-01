package org.springframeworkdemo.demo6.factory;

import org.springframeworkdemo.demo6.entity.BeanDefinition;

public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String name, BeanDefinition bd);

    void removeBeanDefinition(String name);

    BeanDefinition getBeanDefinition(String name);

    boolean containsBeanDefinition(String name);
}