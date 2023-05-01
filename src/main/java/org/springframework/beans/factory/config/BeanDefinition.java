package org.springframework.beans.factory.config;

import org.springframework.beans.PropertyValues;

/**
 * Bean定义，存储Bean为实例化之前的信息，包括类信息、属性信息等
 *
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public class BeanDefinition {

	private Class beanClass;

	private PropertyValues propertyValues;

	public BeanDefinition(Class beanClass) {
		this(beanClass, null);
	}

	public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
		this.beanClass = beanClass;
		this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}

	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}
}
