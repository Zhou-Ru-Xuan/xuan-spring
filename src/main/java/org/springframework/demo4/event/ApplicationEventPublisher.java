package org.springframework.demo4.event;


public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}