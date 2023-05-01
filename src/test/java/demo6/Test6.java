package demo6;

import org.springframeworkdemo.demo6.ClassPathXmlApplicationContext;
import org.springframeworkdemo.demo6.exception.BeansException;
import org.springframeworkdemo.demo6.service.AService;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-28
 **/

/**
 * 1.解析xml
 * 2.注入bean
 * 3.获取bean
 * 相比于demo5，demo6的属性可以注入别的bean
 */
public class Test6 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans6.xml");
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
