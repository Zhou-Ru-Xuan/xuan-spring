package org.springframeworkdemo.demo6.event;


public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}