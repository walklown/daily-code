package com.zzp.learn.springboot.aop1.sensitive;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Aspect切面
 *
 * @author Walklown
 * @date 2023-03-16
 */
@Aspect
@Order(20)
@Component
public class AspectJDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectJDemo.class);

    @Pointcut("@annotation(com.zzp.learn.springboot.aop1.sensitive.AspectPoint)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object sensitiveAround(ProceedingJoinPoint pjp) throws Throwable {
        Object returnValue = pjp.proceed();
        return returnValue;
    }
}
