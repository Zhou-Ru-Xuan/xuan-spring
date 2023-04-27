package demo3;

import org.springframework.demo3.ClassPathXmlApplicationContext;
import org.springframework.demo3.exception.BeansException;
import org.springframework.testservice.AService;

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