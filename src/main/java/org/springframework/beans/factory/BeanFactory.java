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

}
