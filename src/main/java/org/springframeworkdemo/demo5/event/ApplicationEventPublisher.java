package org.springframeworkdemo.demo5.event;


public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}