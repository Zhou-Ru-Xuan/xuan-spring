package demo2;

import org.springframework.demo2.ClassPathXmlApplicationContext;
import org.springframework.demo2.exception.BeansException;
import org.springframework.testservice.AService;

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