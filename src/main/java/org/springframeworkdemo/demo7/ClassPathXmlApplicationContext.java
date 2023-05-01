package org.springframeworkdemo.demo7;

import org.springframeworkdemo.demo7.event.ApplicationEvent;
import org.springframeworkdemo.demo7.event.ApplicationEventPublisher;
import org.springframeworkdemo.demo7.exception.BeansException;
import org.springframeworkdemo.demo7.factory.BeanFactory;
import org.springframeworkdemo.demo7.factory.SimpleBeanFactory;
import org.springframeworkdemo.demo7.reader.XmlBeanDefinitionReader;
import org.springframeworkdemo.demo7.resource.ClassPathXmlResource;
import org.springframeworkdemo.demo7.resource.Resource;

/**
 * 1.解析 XML 文件中的内容。
 * 2.加载解析的内容，构建 BeanDefinition。
 * 3.读取 BeanDefinition 的配置信息，实例化 Bean，然后把它注入到 BeanFactory 容器中。
 */
public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {
    SimpleBeanFactory beanFactory;

    XmlBeanDefinitionReader reader;

    Resource resource;

    public ClassPathXmlApplicationContext(String fileName) {
        this(fileName, true);
    }

    //context负责整合容器的启动过程，读外部配置，解析Bean定义，创建BeanFactory
    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh) {
        this.resource = new ClassPathXmlResource(fileName);
        this.beanFactory = new SimpleBeanFactory();
        this.reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        if (isRefresh) {
            this.beanFactory.refresh();
        }
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
