package com.walklown.learn.jarkata.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class UserSpringDemo {

    public static void main(String[] args) throws NamingException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        User user = applicationContext.getBean("user", User.class);

        //EJB = Local Bean + Remote Bean
        //Local Bean : 当前容器
        //Remote Bean :远程RPC = RMI + Interface = Dubbo、Spring Cloud
        //Service = Bean
        //@Service = Bean

        // Spring Container = Local Bean
        //  Spring Remove Bean = Spring Cloud = Feign + Service Discovery

        //Tomcat
        Context context = new InitialContext();
        context.lookup("java");
    }
}
