package org.springframework.demo8;

import org.springframework.demo8.exception.BeansException;
import org.springframework.demo8.factory.AutowireCapableBeanFactory;
import org.springframework.demo8.factory.BeanFactory;
import org.springframework.demo8.processor.AutowiredAnnotationBeanPostProcessor;
import org.springframework.demo8.reader.XmlBeanDefinitionReader;
import org.springframework.demo8.resource.ClassPathXmlResource;
import org.springframework.demo8.resource.Resource;

import java.util.List;

/**
 * 1.解析 XML 文件中的内容。
 * 2.加载解析的内容，构建 BeanDefinition。
 * 3.读取 BeanDefinition 的配置信息，实例化 Bean，然后把它注入到 BeanFactory 容器中。
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    AutowireCapableBeanFactory beanFactory;

    XmlBeanDefinitionReader reader;

    Resource resource;

    public ClassPathXmlApplicationContext(String fileName) {
        this(fileName, true);
    }

    //context负责整合容器的启动过程，读外部配置，解析Bean定义，创建BeanFactory
    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh) {
        this.resource = new ClassPathXmlResource(fileName);
        this.beanFactory = new AutowireCapableBeanFactory();
        this.reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        if (isRefresh) {
            try {
                this.refresh();
            } catch (BeansException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void refresh() throws BeansException, IllegalStateException {
        // Register bean processors that intercept bean creation.
        this.beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());//每次都new一个Processor？
        // Initialize other special beans in specific context subclasses.
        this.beanFactory.refresh();
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
}
