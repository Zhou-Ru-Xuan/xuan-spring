package demo1;

import org.springframework.demo1.ClassPathXmlApplicationContext;
import org.springframework.testservice.AService;

public class Test1 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        AService aService = (AService) ctx.getBean("aservice");
        aService.sayHello();
    }
} 