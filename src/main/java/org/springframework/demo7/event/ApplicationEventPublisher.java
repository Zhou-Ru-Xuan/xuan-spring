package org.springframework.demo7.event;


public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}