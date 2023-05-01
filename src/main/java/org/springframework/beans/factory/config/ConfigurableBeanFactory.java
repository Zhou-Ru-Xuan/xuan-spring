package org.springframework.beans.factory.config;

import org.springframework.beans.factory.BeanFactory;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 */
public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {

	/**
	 * @param beanPostProcessor
	 */
	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
