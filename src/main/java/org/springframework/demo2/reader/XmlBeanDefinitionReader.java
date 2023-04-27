package org.springframework.demo2.reader;

import org.dom4j.Element;
import org.springframework.demo2.entity.BeanDefinition;
import org.springframework.demo2.factory.BeanFactory;
import org.springframework.demo2.resource.Resource;

public class XmlBeanDefinitionReader {
    BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
            this.beanFactory.registryBean(beanDefinition);
        }
    }
}