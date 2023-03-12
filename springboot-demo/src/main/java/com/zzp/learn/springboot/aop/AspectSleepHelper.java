package com.zzp.learn.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * ajc.exe 可以理解为 javac.exe 命令，都用于编译 Java 程序，区别是 ajc.exe 命令可识别 AspectJ 的语法；我们可以将
 * ajc.exe 当成一个增强版的 javac.exe 命令.执行ajc命令后的 SayHelloService.class 文件不是由原来的
 * SayHelloService.java 文件编译得到的，该 SayHelloService.class 里新增了打印日志的内容——这表明 AspectJ 在编
 * 译时“自动”编译得到了一个新类，这个新类增强了原有的 SayHelloService.java 类的功能，因此 AspectJ 通常被称为编译时
 * 增强的 AOP 框架。
 */
@Aspect
@Component
public class AspectSleepHelper {

    @Before("execution(* com.zzp.learn.springboot.aop.impl.Me.sleep(..))")
    public void before(JoinPoint joinPoint) throws Throwable {
        System.out.println("睡觉前要脱衣服！");
    }

    @After("execution(* com.zzp.learn.springboot.aop.impl.Me.sleep(..))")
    public void afterReturning(JoinPoint joinPoint) throws Throwable {
        System.out.println("起床后要穿衣服！");
    }
}
