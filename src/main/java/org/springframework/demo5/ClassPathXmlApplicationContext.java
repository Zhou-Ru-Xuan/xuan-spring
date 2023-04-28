package org.springframework.demo5;

import org.springframework.demo5.event.ApplicationEvent;
import org.springframework.demo5.event.ApplicationEventPublisher;
import org.springframework.demo5.exception.BeansException;
import org.springframework.demo5.factory.BeanFactory;
import org.springframework.demo5.factory.SimpleBeanFactory;
import org.springframework.demo5.reader.XmlBeanDefinitionReader;
import org.springframework.demo5.resource.ClassPathXmlResource;
import org.springframework.demo5.resource.Resource;

/**
 * 1.解析 XML 文件中的内容。
 * 2.加载解析的内容，构建 BeanDefinition。
 * 3.读取 BeanDefinition 的配置信息，实例化 Bean，然后把它注入到 BeanFactory 容器中。
 */
public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {
    SimpleBeanFactory beanFactory;

    XmlBeanDefinitionReader reader;

    Resource resource;

    //context负责整合容器的启动过程，读外部配置，解析Bean定义，创建BeanFactory
    public ClassPathXmlApplicationContext(String fileName) {
        this.resource = new ClassPathXmlResource(fileName);
        this.beanFactory = new SimpleBeanFactory();
        this.reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    @Override
    public Boolean containsBean(String name) {
        return this.beanFactory.containsBean(name);
    }

    @Override
    public boolean isSingleton(String name) {
        return beanFactory.isSingleton(name);
    }

    @Override
    public boolean isPrototype(String name) {
        return beanFactory.isPrototype(name);
    }

    @Override
    public Class<?> getType(String name) {
        return beanFactory.getType(name);
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        System.out.println("publishEvent......");
    }
}
