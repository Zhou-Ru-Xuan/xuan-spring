package org.springframework.beans.factory.config;

/**
 * Bean引用，记录类之间的依赖关系
 *
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public class BeanReference {

	private final String beanName;


	public BeanReference(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return beanName;
	}
}
