package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * Bean工厂，用于获取bean
 *
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public interface BeanFactory {

    Object getBean(String name) throws BeansException;


    /**
     * 根据名称和类型查找bean
     *
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;


    <T> T getBean(Class<T> requiredType) throws BeansException;
}
