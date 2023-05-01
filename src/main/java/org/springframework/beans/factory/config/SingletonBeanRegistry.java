package org.springframework.beans.factory.config;

/**
 * 注册单例
 *
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public interface SingletonBeanRegistry {

	Object getSingleton(String beanName);
}
