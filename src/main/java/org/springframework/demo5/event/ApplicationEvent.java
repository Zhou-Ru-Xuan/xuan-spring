package org.springframework.demo5.event;

import java.util.EventObject;

public class ApplicationEvent extends EventObject {
    private static final long serialVersionUID = 1L;

    public ApplicationEvent(Object arg0) {
        super(arg0);
    }
}