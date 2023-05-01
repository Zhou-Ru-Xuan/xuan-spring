package org.springframework.test.ioc.service;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public class HelloService {

	public String sayHello() {
		System.out.println("hello");
		return "hello";
	}
}
