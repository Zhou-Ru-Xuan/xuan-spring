package org.springframework.demo8.entity;


public class BeanDefinition {
    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";
    private boolean lazyInit = false; //表示 Bean 要不要在加载的时候初始化
    private String[] dependsOn;//记录 Bean 之间的依赖关系
    private ConstructorArgumentValues constructorArgumentValues;
    private PropertyValues propertyValues;
    private String initMethodName; //当一个 Bean 构造好并实例化之后是否要让框架调用初始化方法
    private volatile Object beanClass;
    private String id;
    private String className;
    private String scope = SCOPE_SINGLETON; //单例模式还是原型模式

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public boolean isSingleton() {
        return SCOPE_SINGLETON.equals(this.scope);
    }

    public boolean isPrototype() {
        return SCOPE_PROTOTYPE.equals(this.scope);
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String[] getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(String[] dependsOn) {
        this.dependsOn = dependsOn;
    }

    public ConstructorArgumentValues getConstructorArgumentValues() {
        return constructorArgumentValues;
    }

    public void setConstructorArgumentValues(ConstructorArgumentValues constructorArgumentValues) {
        this.constructorArgumentValues = constructorArgumentValues;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public Object getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Object beanClass) {
        this.beanClass = beanClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}