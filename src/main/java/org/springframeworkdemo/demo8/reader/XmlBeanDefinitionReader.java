package org.springframeworkdemo.demo8.reader;

import org.dom4j.Element;
import org.springframeworkdemo.demo8.entity.*;
import org.springframeworkdemo.demo8.factory.AutowireCapableBeanFactory;
import org.springframeworkdemo.demo8.resource.Resource;

import java.util.ArrayList;
import java.util.List;

public class XmlBeanDefinitionReader {
    AutowireCapableBeanFactory beanFactory;

    public XmlBeanDefinitionReader(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }


    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
            // handle constructor
            List<Element> constructorElements = element.elements("constructor-arg");
            ConstructorArgumentValues AVS = new ConstructorArgumentValues();
            for (int i = 0; i < constructorElements.size(); i++) {
                Element e = constructorElements.get(i);
                String aType = e.attributeValue("type");
                String aName = e.attributeValue("name");
                String aValue = e.attributeValue("value");
                AVS.addArgumentValue(i, new ConstructorArgumentValue(aType, aName, aValue));
            }

            beanDefinition.setConstructorArgumentValues(AVS);

            // handle properties
            List<Element> propertyElements = element.elements("property");
            PropertyValues PVS = new PropertyValues();
            List<String> refs = new ArrayList<>();
            for (Element e : propertyElements) {
                String pType = e.attributeValue("type");
                String pName = e.attributeValue("name");
                String pValue = e.attributeValue("value");
                String pRef = e.attributeValue("ref");
                String pV = "";
                boolean isRef = false;
                if (pValue != null && !pValue.equals("")) {
                    pV = pValue;
                } else if (pRef != null && !pRef.equals("")) {
                    isRef = true;
                    pV = pRef;
                    refs.add(pRef);
                }
                PVS.addPropertyValue(new PropertyValue(pType, pName, pV, isRef));
            }
            beanDefinition.setPropertyValues(PVS);

            String[] refArray = refs.toArray(new String[0]);
            beanDefinition.setDependsOn(refArray);
            this.beanFactory.registerBeanDefinition(beanID, beanDefinition);
        }
    }
}

