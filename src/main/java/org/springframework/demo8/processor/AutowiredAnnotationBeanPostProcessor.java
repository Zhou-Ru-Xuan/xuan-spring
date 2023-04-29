package org.springframework.demo8.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.demo8.annotation.Autowired;
import org.springframework.demo8.exception.BeansException;
import org.springframework.demo8.factory.AutowireCapableBeanFactory;

import java.lang.reflect.Field;

@Slf4j
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {
    private AutowireCapableBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object result = bean;

        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            //对每一个属性进行判断，如果带有@Autowired注解则进行处理
            for (Field field : fields) {
                boolean isAutowired = field.isAnnotationPresent(Autowired.class);
                if (isAutowired) {
                    //根据属性名查找同名的bean
                    String fieldName = field.getName();
                    Object autowiredObj = this.getBeanFactory().getBean(fieldName);
                    //设置属性值，完成注入
                    try {
                        field.setAccessible(true);
                        field.set(bean, autowiredObj);
                        log.info("autowired fieldName = {} for beanName = {}", fieldName, beanName);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return null;
    }

    public AutowireCapableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
