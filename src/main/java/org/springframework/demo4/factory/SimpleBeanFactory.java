package org.springframework.demo4.factory;

import org.springframework.demo4.entity.BeanDefinition;
import org.springframework.demo4.exception.BeansException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 接着单例之后，再实现接口BeanDefinitionRegistry，这里相当于把beanDefinition的注入通过接口BeanDefinitionRegistry规范化。
 * <p></p>
 * 其实现在看来，直接把单例的DefaultSingletonBeanRegistry和BeanDefinitionRegistry的实现类作为SimpleBeanFactory的属性，通过组合来做，应该会更好一点，直接把SimpleBeanFactory架空
 * <p></p>
 * 看看后面怎么发展吧
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private final List<String> beanDefinitionNames = new ArrayList<>();

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name, beanDefinition);
        this.beanDefinitionNames.add(name);
        if (!beanDefinition.isLazyInit()) {
            try {
                getBean(name);
            } catch (BeansException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //getBean，容器的核心方法
    public Object getBean(String beanName) throws BeansException {
        //先尝试直接拿bean实例
        if (containsBean(beanName)) {
            return this.getSingleton(beanName);
        }
        //如果此时还没有这个bean的实例，则获取它的定义来创建实例
        Object singleton;
        //获取bean的定义
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
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

    @Override
    public boolean isSingleton(String name) {
        return this.beanDefinitionMap.get(name).isSingleton();
    }

    @Override
    public boolean isPrototype(String name) {
        return this.beanDefinitionMap.get(name).isPrototype();
    }

    @Override
    public Class<?> getType(String name) {
        return this.beanDefinitionMap.get(name).getClass();
    }

    @Override
    public void removeBeanDefinition(String name) {
        this.beanDefinitionMap.remove(name);
        this.beanDefinitionNames.remove(name);
        this.removeSingleton(name);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitionMap.containsKey(name);
    }
}
