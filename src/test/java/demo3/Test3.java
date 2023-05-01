package demo3;

import org.springframeworkdemo.demo3.ClassPathXmlApplicationContext;
import org.springframeworkdemo.demo3.exception.BeansException;
import org.springframeworkdemo.testservice.AService;

/**
 * init-and-destroy-method.xml.解析xml
 * 2.注入bean
 * 3.获取bean
 * 相比于demo2，demo3实现了单例Map的存储
 */
public class Test3 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        AService aService = null;
        try {
            aService = (AService) ctx.getBean("aservice");
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
        aService.sayHello();

        try {
            AService aService2 = (AService) ctx.getBean("aservice");
            System.out.println("aService == aService2:" + (aService == aService2));
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }

    }
} 