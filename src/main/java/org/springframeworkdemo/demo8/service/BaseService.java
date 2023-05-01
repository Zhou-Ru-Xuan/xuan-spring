package org.springframeworkdemo.demo8.service;

import org.springframeworkdemo.demo8.annotation.Autowired;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-27
 **/
public class BaseService implements AService {

    @Autowired
    AServiceImpl service;

    public BaseService() {

    }

    public BaseService(AServiceImpl service) {
        this.service = service;
    }

    public AService getService() {
        return service;
    }

    public void setService(AServiceImpl service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "BaseService{" +
                "service.name=" + (service == null ? null : service.getName()) +
                '}';
    }

    @Override
    public void sayHello() {

    }
}
