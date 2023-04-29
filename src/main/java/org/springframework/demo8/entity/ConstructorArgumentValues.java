package org.springframework.demo8.entity;


import java.util.*;

public class ConstructorArgumentValues {
    private final Map<Integer, ConstructorArgumentValue> indexedArgumentValues = new HashMap<>(0);
    private final List<ConstructorArgumentValue> genericArgumentValues = new LinkedList<>();

    public ConstructorArgumentValues() {
    }

    public void addArgumentValue(Integer key, ConstructorArgumentValue newValue) {
        this.indexedArgumentValues.put(key, newValue);
        this.genericArgumentValues.add(newValue);
    }

    public boolean hasIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.containsKey(index);
    }

    public ConstructorArgumentValue getIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.get(index);
    }

    public void addGenericArgumentValue(Object value, String type, String name) {
        this.genericArgumentValues.add(new ConstructorArgumentValue(type, name, value));
    }

    private void addGenericArgumentValue(ConstructorArgumentValue newValue) {
        if (newValue.getName() != null) {
            for (Iterator<ConstructorArgumentValue> it = this.genericArgumentValues.iterator(); it.hasNext(); ) {
                ConstructorArgumentValue currentValue = it.next();
                if (newValue.getName().equals(currentValue.getName())) {
                    it.remove();
                }
            }
        }
        this.genericArgumentValues.add(newValue);
    }

    private ConstructorArgumentValue getGenericArgumentValue(String requiredName) {
        for (ConstructorArgumentValue valueHolder : this.genericArgumentValues) {
            if (valueHolder.getName() != null && (requiredName == null || !valueHolder.getName().equals(requiredName))) {
                continue;
            }
            return valueHolder;
        }
        return null;
    }

    public int getArgumentCount() {
        return this.genericArgumentValues.size();
    }

    public boolean isEmpty() {
        return this.genericArgumentValues.isEmpty();
    }
}