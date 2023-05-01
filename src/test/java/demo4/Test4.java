package demo4;

import org.springframeworkdemo.demo4.ClassPathXmlApplicationContext;
import org.springframeworkdemo.demo4.exception.BeansException;
import org.springframeworkdemo.testservice.AService;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-28
 **/
/**
 * 1.解析xml
 * 2.注入bean
 * 3.获取bean
 * 相比于demo3，demo4把注册beanDefinition的能力抽象了出来，将创建空壳对象与属性注入分开
 */
public class Test4 {
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
