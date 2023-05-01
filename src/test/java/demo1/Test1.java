package demo1;

import org.springframeworkdemo.demo1.ClassPathXmlApplicationContext;
import org.springframeworkdemo.testservice.AService;

/**
 * 1.解析xml
 * 2.注入bean
 * 3.获取bean
 */
public class Test1 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        AService aService = (AService) ctx.getBean("aservice");
        aService.sayHello();
    }
} 