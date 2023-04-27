package org.springframework.demo3.factory;

import org.springframework.demo3.entity.BeanDefinition;
import org.springframework.demo3.exception.BeansException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SimpleBeanFactory仅存储初始化时的bean实例。
 * <p><p/>因为继承了DefaultSingletonBeanRegistry，因此当使用这个工厂时，我们优先使用DefaultSingletonBeanRegistry里面的单例对象。
 * <p><p/>这里利用继承关系将功能转移到DefaultSingletonBeanRegistry，但实际上，用组合可能更好一点？以后再看看。
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    private final Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);

    //getBean，容器的核心方法
    public Object getBean(String beanName) throws BeansException {
        //先尝试直接拿bean实例
        if (containsBean(beanName)) {
            return this.getSingleton(beanName);
        }
        //如果此时还没有这个bean的实例，则获取它的定义来创建实例
        Object singleton;
        //获取bean的定义
        BeanDefinition beanDefinition = beanDefinitions.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean.");
        }
        try {
            singleton = Class.forName(beanDefinition.getClassName()).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        //新注册这个bean实例
        this.registerSingleton(beanName, singleton);

        return singleton;
    }

    public Boolean containsBean(String name) {
        return containsSingleton(name);
    }

    public void registerBean(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitions.put(beanName, beanDefinition);
    }
}
