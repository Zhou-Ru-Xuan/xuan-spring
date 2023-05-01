package org.springframeworkdemo.demo4.reader;

import org.dom4j.Element;
import org.springframeworkdemo.demo4.entity.BeanDefinition;
import org.springframeworkdemo.demo4.factory.SimpleBeanFactory;
import org.springframeworkdemo.demo4.resource.Resource;

public class XmlBeanDefinitionReader {
    SimpleBeanFactory simpleBeanFactory; //这里其实是不合理的，不应注入具体的实现类。理应用BeanDefinitionRegistry接口才对。但没办法，只能这样。看看后面有没有办法解决

    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.simpleBeanFactory = simpleBeanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
            this.simpleBeanFactory.registerBeanDefinition(beanID, beanDefinition);
        }
    }
}
