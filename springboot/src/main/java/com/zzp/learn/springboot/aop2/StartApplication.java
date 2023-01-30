
package com.zzp.learn.springboot.aop2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 启动类
 *
 * @author 守愚（张智沛）
 * @date 2021-01-07
 */
@SpringBootApplication(scanBasePackages = {"com.zzp.learn.springboot.aop2"})
@EnableAspectJAutoProxy
public class StartApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(StartApplication.class)
                .run(args);
        Child1 child1 = (Child1) applicationContext.getBean("child1");
        child1.sayHello();
        child1.sayYes();
        child1.run();
        Child2 child2 = (Child2) applicationContext.getBean("child2");
        child2.sayHello();
        child2.sayYes();
        child2.run();
        Parent parent = (Parent) applicationContext.getBean("parent");
        parent.sayHello();
        parent.sayYes();
        parent.run();
    }

}
