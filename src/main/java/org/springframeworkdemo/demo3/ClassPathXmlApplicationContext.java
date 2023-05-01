package org.springframeworkdemo.demo3;

import org.springframeworkdemo.demo3.entity.BeanDefinition;
import org.springframeworkdemo.demo3.exception.BeansException;
import org.springframeworkdemo.demo3.factory.BeanFactory;
import org.springframeworkdemo.demo3.factory.SimpleBeanFactory;
import org.springframeworkdemo.demo3.reader.XmlBeanDefinitionReader;
import org.springframeworkdemo.demo3.resource.ClassPathXmlResource;
import org.springframeworkdemo.demo3.resource.Resource;

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
        //demo3使用新的bean工厂
        this.beanFactory = new SimpleBeanFactory();
        this.reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
    }

    //context再对外提供一个getBean，底下就是调用的BeanFactory对应的方法
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    @Override
    public Boolean containsBean(String name) {
        return this.beanFactory.containsBean(name);
    }

    @Override
    public void registerBean(String beanName, BeanDefinition obj) {
        this.beanFactory.registerBean(beanName, obj);
    }

}
