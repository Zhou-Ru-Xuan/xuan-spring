package demo7;

import org.springframework.demo7.ClassPathXmlApplicationContext;
import org.springframework.demo7.exception.BeansException;
import org.springframework.demo7.service.AService;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-28
 **/

/**
 * 1.解析xml
 * 2.注入bean
 * 3.获取bean
 * 相比于demo6，demo7可以解决循环依赖的问题
 */
public class Test7 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans7.xml");
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
