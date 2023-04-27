package org.springframework.demo4.reader;

import org.dom4j.Element;
import org.springframework.demo4.entity.BeanDefinition;
import org.springframework.demo4.factory.SimpleBeanFactory;
import org.springframework.demo4.resource.Resource;

public class XmlBeanDefinitionReader {
    SimpleBeanFactory simpleBeanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.simpleBeanFactory = simpleBeanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
            this.simpleBeanFactory.registerBean(beanID, beanDefinition);
        }
    }
}
