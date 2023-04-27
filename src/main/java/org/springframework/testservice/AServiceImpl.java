package org.springframework.testservice;

public class AServiceImpl implements AService {

    private String property1;

    private String name;
    private int level;

    public AServiceImpl() {

    }

    public AServiceImpl(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public void sayHello() {
        System.out.println("a service 1 say hello");
    }

} 