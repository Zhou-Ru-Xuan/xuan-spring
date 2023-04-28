package demo5;

import org.springframework.demo5.ClassPathXmlApplicationContext;
import org.springframework.demo5.exception.BeansException;
import org.springframework.testservice.AService;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-28
 **/

/**
 * 1.解析xml
 * 2.注入bean
 * 3.获取bean
 * 相比于demo4，demo5可以将解析的属性注入到bean中返回
 */
public class Test5 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans5.xml");
        AService aService = null;
        try {
            aService = (AService) ctx.getBean("aserviceC");
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
        aService.sayHello();

        try {
            AService aService2 = (AService) ctx.getBean("aserviceP");
            aService2.sayHello();
            System.out.println("aService == aService2:" + (aService == aService2));
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }
}
