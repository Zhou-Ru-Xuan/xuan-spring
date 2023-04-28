package org.springframework.testservice;

public class AServiceImpl implements AService {

    private String property1;

    private String name;
    private int level;

    public AServiceImpl() {

    }

    public AServiceImpl(String name) {
        this.name = name;
    }

    public AServiceImpl(int level) {
        this.level = level;
    }

    public AServiceImpl(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public AServiceImpl(String property1, String name, int level) {
        this.property1 = property1;
        this.name = name;
        this.level = level;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
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

    @Override
    public String toString() {
        return "AServiceImpl{" +
                "property1='" + property1 + '\'' +
                ", name='" + name + '\'' +
                ", level=" + level +
                '}';
    }

    public void sayHello() {
        System.out.println(this);
    }

} 