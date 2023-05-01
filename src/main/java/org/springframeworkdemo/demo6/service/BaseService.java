package org.springframeworkdemo.demo6.service;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-27
 **/
public class BaseService implements AService {

    AService aService;
    public BaseService() {

    }

    public BaseService(AService aService) {
        this.aService = aService;
    }

    public AService getaService() {
        return aService;
    }

    public void setaService(AService aService) {
        this.aService = aService;
    }

    @Override
    public String toString() {
        return "BaseService{" +
                "aService=" + aService +
                '}';
    }

    @Override
    public void sayHello() {
        System.out.println(this);
    }
}
