package org.springframework.context;

import org.springframework.beans.BeansException;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public interface ConfigurableApplicationContext extends ApplicationContext {

	/**
	 * 刷新容器
	 *
	 * @throws BeansException
	 */
	void refresh() throws BeansException;
}
