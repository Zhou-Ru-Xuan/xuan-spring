package org.springframework.beans.factory.config;

/**
 * 单例仓库
 *
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);


    void addSingleton(String beanName, Object singletonObject);

}
