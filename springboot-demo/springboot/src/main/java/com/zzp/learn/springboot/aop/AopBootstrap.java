package com.zzp.learn.springboot.aop;

import com.zzp.learn.springboot.aop.impl.ComponentDemo;
import com.zzp.learn.springboot.aop.impl.ConfigurationDemo;
import com.zzp.learn.springboot.aop.impl.Sleepable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * AOP（Aspect Orient Programming），作为面向对象编程的一种补充，广泛应用于处理一些具有横切性质的系统级服务，如事务
 * 管理、安全检查、缓存、对象池管理等。AOP 实现的关键就在于 AOP 框架自动创建的 AOP 代理，AOP 代理则可分为静态代理和动
 * 态代理两大类，其中静态代理是指使用 AOP 框架提供的命令进行编译，从而在编译阶段就可生成 AOP 代理类，因此也称为编译时增
 * 强；而动态代理则在运行时借助于 JDK 动态代理、CGLIB 等在内存中“临时”生成 AOP 动态代理类，因此也被称为运行时增强。
 */
@SpringBootApplication(scanBasePackages = "com.zzp.learn.springboot.aop")
@Slf4j
@RestController
public class AopBootstrap {

    @Autowired
    private ConfigurationDemo configurationDemo;

    @Autowired
    private ComponentDemo componentDemo;

    // GenericApplicationListener
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AopBootstrap.class);
        ConfigurableApplicationContext context = springApplication.run(args);

        ClassLoader classLoader = context.getClassLoader();
        List<Sleepable> list = SpringFactoriesLoader.loadFactories(Sleepable.class, classLoader);
        for (Sleepable sleepable : list) {
            sleepable.sleep();
        }
    }

    @GetMapping(path = "test")
    public String mapping() {
        return "success";
    }
}