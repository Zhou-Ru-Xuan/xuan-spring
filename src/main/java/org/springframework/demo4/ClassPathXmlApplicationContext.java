package org.springframework.demo4;

import org.springframework.demo4.event.ApplicationEvent;
import org.springframework.demo4.event.ApplicationEventPublisher;
import org.springframework.demo4.exception.BeansException;
import org.springframework.demo4.factory.BeanFactory;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-27
 **/
public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {
    @Override
    public void publishEvent(ApplicationEvent event) {

    }

    @Override
    public Object getBean(String name) throws BeansException {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public boolean isSingleton(String name) {
        return false;
    }

    @Override
    public boolean isPrototype(String name) {
        return false;
    }

    @Override
    public Class<?> getType(String name) {
        return null;
    }
}
