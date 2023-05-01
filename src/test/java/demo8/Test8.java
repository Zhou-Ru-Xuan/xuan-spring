package demo8;

import org.springframeworkdemo.demo8.ClassPathXmlApplicationContext;
import org.springframeworkdemo.demo8.exception.BeansException;
import org.springframeworkdemo.demo8.service.AService;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-28
 **/

/**
 * 1.解析xml
 * 2.注入bean
 * 3.获取bean
 * 相比于demo7，demo8可以通过Autowired注入属性
 */
public class Test8 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans8.xml");
        AService aService = null;
        try {
            aService = (AService) ctx.getBean("service");
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
        aService.sayHello();

        try {
            AService aService2 = (AService) ctx.getBean("service");
            System.out.println("aService == aService2:" + (aService == aService2));

        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }
}
