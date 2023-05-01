package org.springframeworkdemo.demo5.reader;

import org.dom4j.Element;
import org.springframeworkdemo.demo5.entity.*;
import org.springframeworkdemo.demo5.factory.SimpleBeanFactory;
import org.springframeworkdemo.demo5.resource.Resource;

import java.util.List;

public class XmlBeanDefinitionReader {
    SimpleBeanFactory simpleBeanFactory; //这里其实是不合理的，不应注入具体的实现类。理应用BeanDefinitionRegistry接口才对。但没办法，只能这样。看看后面有没有办法解决

    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.simpleBeanFactory = simpleBeanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {

            //处理对象
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);

            //处理属性
            List<Element> propertyElements = element.elements("property");
            PropertyValues PVS = new PropertyValues();
            for (Element e : propertyElements) {
                String pType = e.attributeValue("type");
                String pName = e.attributeValue("name");
                String pValue = e.attributeValue("value");
                PVS.addPropertyValue(new PropertyValue(pType, pName, pValue));
            }
            beanDefinition.setPropertyValues(PVS);

            //处理构造器参数
            List<Element> constructorElements = element.elements("constructor-arg");
            ArgumentValues AVS = new ArgumentValues();
            for (int i = 0; i < constructorElements.size(); i++) {
                Element e = constructorElements.get(i);
                String aType = e.attributeValue("type");
                String aName = e.attributeValue("name");
                String aValue = e.attributeValue("value");
                AVS.addArgumentValue(i, new ArgumentValue(aType, aName, aValue));
            }
            beanDefinition.setConstructorArgumentValues(AVS);

            this.simpleBeanFactory.registerBeanDefinition(beanID, beanDefinition);
        }
    }
}

