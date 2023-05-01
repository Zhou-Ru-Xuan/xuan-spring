package org.springframeworkdemo.demo4.event;


public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}