package org.springframework.demo5.event;


public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}