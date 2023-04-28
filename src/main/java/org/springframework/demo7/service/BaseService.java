package org.springframework.demo7.service;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-27
 **/
public class BaseService implements AService {

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
    public void sayHello() {

    }
}
