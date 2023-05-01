package org.springframeworkdemo.demo7.event;


public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}