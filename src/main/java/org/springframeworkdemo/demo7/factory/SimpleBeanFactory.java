package org.springframeworkdemo.demo7.factory;

import org.springframeworkdemo.demo7.entity.*;
import org.springframeworkdemo.demo7.exception.BeansException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 接着单例之后，再实现接口BeanDefinitionRegistry，这里相当于把beanDefinition的注入通过接口BeanDefinitionRegistry规范化。
 * <p></p>
 * 其实现在看来，直接把单例的DefaultSingletonBeanRegistry和BeanDefinitionRegistry的实现类作为SimpleBeanFactory的属性，通过组合来做，应该会更好一点，直接把SimpleBeanFactory架空
 * <p></p>
 * 看看后面怎么发展吧
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private final Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>(256);

    private final List<String> beanDefinitionNames = new ArrayList<>();

    /**
     * 将所有从xml注入的beanDefinition注册到单例Map中
     */
    public void refresh() {
        for (String beanName : beanDefinitionNames) {
            try {
                getBean(beanName);
            } catch (BeansException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name, beanDefinition);
        this.beanDefinitionNames.add(name);
        //这样写会强制依赖加载顺序的，不能这样写
        //if (!beanDefinition.isLazyInit()) {
        //    try {
        //        getBean(name);
        //    } catch (BeansException e) {
        //        throw new RuntimeException(e);
        //    }
        //}
    }

    //getBean，容器的核心方法
    public Object getBean(String beanName) throws BeansException {
        //先尝试直接拿bean实例
        if (this.containsSingleton(beanName)) {
            return this.getSingleton(beanName);
        }
        //如果没有实例，则尝试从毛胚实例中获取
        Object singleton = this.earlySingletonObjects.get(beanName);
        if (singleton == null) {
            //获取bean的定义
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition == null) {
                throw new BeansException("No bean.");
            }
            singleton = this.createBean(beanDefinition);
            //新注册这个bean实例
            this.registerSingleton(beanName, singleton);
            // 预留beanPostProcessor位置
            // step 1: postProcessBeforeInitialization
            // step 2: afterPropertiesSet
            // step 3: init-method
            // step 4: postProcessAfterInitialization
        }
        return singleton;
    }

    private Object createBean(BeanDefinition beanDefinition) {
        Class<?> clz = null;
        Object obj = this.doCreateBean(beanDefinition);
        //存放到毛胚实例缓存中
        this.earlySingletonObjects.put(beanDefinition.getId(), obj);
        try {
            clz = Class.forName(beanDefinition.getClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 处理属性
        this.handleProperties(beanDefinition, clz, obj);
        return obj;
    }

    /**
     * doCreateBean创建毛胚实例，仅仅调用构造方法，没有进行属性处理
     *
     * @param bd
     * @return
     */
    private Object doCreateBean(BeanDefinition bd) {
        Class<?> clz = null;
        Object obj = null;
        Constructor<?> con = null;

        try {
            clz = Class.forName(bd.getClassName());

            //handle constructor
            ArgumentValues argumentValues = bd.getConstructorArgumentValues();
            if (!argumentValues.isEmpty()) {
                Class<?>[] paramTypes = new Class<?>[argumentValues.getArgumentCount()];
                Object[] paramValues = new Object[argumentValues.getArgumentCount()];
                for (int i = 0; i < argumentValues.getArgumentCount(); i++) {
                    ArgumentValue argumentValue = argumentValues.getIndexedArgumentValue(i);
                    if ("String".equals(argumentValue.getType()) || "java.lang.String".equals(argumentValue.getType())) {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    } else if ("Integer".equals(argumentValue.getType()) || "java.lang.Integer".equals(argumentValue.getType())) {
                        paramTypes[i] = Integer.class;
                        paramValues[i] = Integer.valueOf((String) argumentValue.getValue());
                    } else if ("int".equals(argumentValue.getType())) {
                        //究极坑点int.class和Integer.class必须用Class<Integer>接收，但并不代表int.class和Integer.class是一样的
                        paramTypes[i] = int.class;
                        paramValues[i] = Integer.valueOf((String) argumentValue.getValue());
                    } else {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    }
                }

                con = clz.getConstructor(paramTypes);
                obj = con.newInstance(paramValues);

            } else {
                obj = clz.newInstance();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        System.out.println(bd.getId() + " bean created. " + bd.getClassName() + " : " + obj.toString());
        return obj;

    }

    /**
     * 处理属性
     *
     * @param bd
     * @param clz
     * @param obj
     */
    private void handleProperties(BeanDefinition bd, Class<?> clz, Object obj) {
        // 处理属性
        System.out.println("handle properties for bean : " + bd.getId());
        PropertyValues propertyValues = bd.getPropertyValues();
        //如果有属性
        if (!propertyValues.isEmpty()) {
            for (int i = 0; i < propertyValues.size(); i++) {
                PropertyValue propertyValue = propertyValues.getPropertyValueList().get(i);
                String pName = propertyValue.getName();
                String pType = propertyValue.getType();
                Object pValue = propertyValue.getValue();
                boolean isRef = propertyValue.isRef();
                Class<?>[] paramTypes = new Class<?>[1];
                Object[] paramValues = new Object[1];
                if (!isRef) { //如果不是ref，只是普通属性
                    //对每一个属性，分数据类型分别处理
                    if ("String".equals(pType) || "java.lang.String".equals(pType)) {
                        paramTypes[0] = String.class;
                    } else if ("Integer".equals(pType) || "java.lang.Integer".equals(pType) || "int".equals(pType)) {
                        paramTypes[0] = Integer.class;
                    } else {
                        //默认string
                        paramTypes[0] = String.class;
                    }

                    paramValues[0] = pValue;
                } else { //is ref, create the dependent beans
                    try {
                        paramTypes[0] = Class.forName(pType);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        //再次调用getBean创建ref的bean实例
                        paramValues[0] = this.getBean((String) pValue);
                    } catch (BeansException e) {
                        throw new RuntimeException(e);
                    }
                }

                //按照setXxxx规范查找setter方法，调用setter方法设置属性
                String methodName = "set" + pName.substring(0, 1).toUpperCase() + pName.substring(1);
                Method method = null;
                try {
                    method = clz.getMethod(methodName, paramTypes);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                try {
                    method.invoke(obj, paramValues);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public Boolean containsBean(String name) {
        return this.containsSingleton(name);
    }

    @Override
    public boolean isSingleton(String name) {
        return this.beanDefinitionMap.get(name).isSingleton();
    }

    @Override
    public boolean isPrototype(String name) {
        return this.beanDefinitionMap.get(name).isPrototype();
    }

    @Override
    public Class<?> getType(String name) {
        return this.beanDefinitionMap.get(name).getClass();
    }

    @Override
    public void removeBeanDefinition(String name) {
        this.beanDefinitionMap.remove(name);
        this.beanDefinitionNames.remove(name);
        this.removeSingleton(name);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitionMap.containsKey(name);
    }
}
