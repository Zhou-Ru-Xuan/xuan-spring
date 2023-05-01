package org.springframeworkdemo.demo8.service;

import org.springframeworkdemo.demo8.annotation.Autowired;

public class AServiceImpl implements AService {
    private String name;
    private int level;
    private String property1;
    private String property2;
    @Autowired
    private BaseService baseService;

    public AServiceImpl() {
    }

    public AServiceImpl(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public AServiceImpl(String name, int level, String property1, String property2, BaseService baseService) {
        this.name = name;
        this.level = level;
        this.property1 = property1;
        this.property2 = property2;
        this.baseService = baseService;
    }

    @Override
    public String toString() {
        return "AServiceImpl{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", property1='" + property1 + '\'' +
                ", property2='" + property2 + '\'' +
                ", baseService=" + baseService +
                '}';
    }

    public void sayHello() {

        System.out.println(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public BaseService getBaseService() {
        return baseService;
    }

    public void setBaseService(BaseService baseService) {
        this.baseService = baseService;
    }
}