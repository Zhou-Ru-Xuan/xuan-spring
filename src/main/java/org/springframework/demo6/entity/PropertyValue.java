package org.springframework.demo6.entity;

public class PropertyValue {

    private final String type;

    private final String name;
    private final Object value;

    private final boolean ref;

    public PropertyValue(String type, String name, Object value, boolean ref) {
        this.type = type;
        this.name = name;
        this.value = value;
        this.ref = ref;
    }

    public boolean isRef() {
        return ref;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}