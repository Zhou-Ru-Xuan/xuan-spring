package org.springframework.demo2;

import org.springframework.demo2.entity.BeanDefinition;
import org.springframework.demo2.exception.BeansException;
import org.springframework.demo2.factory.BeanFactory;
import org.springframework.demo2.factory.SimpleBeanFactory;
import org.springframework.demo2.resource.ClassPathXmlResource;
import org.springframework.demo2.resource.Resource;
import org.springframework.demo2.reader.XmlBeanDefinitionReader;

/**
 * 1.解析 XML 文件中的内容。
 * 2.加载解析的内容，构建 BeanDefinition。
 * 3.读取 BeanDefinition 的配置信息，实例化 Bean，然后把它注入到 BeanFactory 容器中。
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    BeanFactory beanFactory;

    XmlBeanDefinitionReader reader;

    Resource resource;

    //context负责整合容器的启动过程，读外部配置，解析Bean定义，创建BeanFactory
    public ClassPathXmlApplicationContext(String fileName) {
        this.resource = new ClassPathXmlResource(fileName);
        this.beanFactory = new SimpleBeanFactory();
        this.reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
    }

    //context再对外提供一个getBean，底下就是调用的BeanFactory对应的方法
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    public void registryBean(BeanDefinition beanDefinition) {
        this.beanFactory.registryBean(beanDefinition);
    }
}
