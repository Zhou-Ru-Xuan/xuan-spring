package demo2;

import org.springframeworkdemo.demo2.ClassPathXmlApplicationContext;
import org.springframeworkdemo.demo2.exception.BeansException;
import org.springframeworkdemo.testservice.AService;

/**
 * 1.解析xml
 * 2.注入bean
 * 3.获取bean
 * 相比于demo1，demo2重构了各部分
 */
public class Test2 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        AService aService = null;
        try {
            aService = (AService) ctx.getBean("aservice");
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
        aService.sayHello();
    }
} 