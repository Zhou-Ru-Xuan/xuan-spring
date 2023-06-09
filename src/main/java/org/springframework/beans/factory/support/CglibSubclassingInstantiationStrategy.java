package org.springframework.beans.factory.support;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    /**
     * 使用CGLIB动态生成子类
     *
     * @param beanDefinition
     * @return
     * @throws BeansException
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        BeanGenerator generator = new BeanGenerator();
        generator.setSuperclass(beanDefinition.getBeanClass());
        return generator.create();
    }
}
