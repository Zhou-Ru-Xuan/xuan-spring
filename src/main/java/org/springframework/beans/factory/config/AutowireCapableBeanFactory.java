package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

/**
 * 使bean工厂拥有Autowire的能力
 *
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行BeanPostProcessors的postProcessBeforeInitialization方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行BeanPostProcessors的postProcessAfterInitialization方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
