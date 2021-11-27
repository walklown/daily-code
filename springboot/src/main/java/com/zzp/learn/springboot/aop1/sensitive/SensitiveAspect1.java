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
 * <p> 脱敏切面
 *
 * @author 【千殇】（【罗玉华】qianshang.luo@tuya.com）
 * @since 2021/8/26 10:37 下午
 */
@Aspect
@Order(20)
@Component
public class SensitiveAspect1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveAspect1.class);

    @Pointcut("@annotation(com.tuya.tianlu.starter.sensitive.Sensitive)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object sensitiveAround(ProceedingJoinPoint pjp) throws Throwable {
        Object returnValue = pjp.proceed();
        return returnValue;
    }
}
