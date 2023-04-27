package org.springframework.demo6.event;


public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}