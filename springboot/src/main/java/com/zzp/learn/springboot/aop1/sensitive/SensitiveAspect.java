package com.zzp.learn.springboot.aop1.sensitive;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.PriorityOrdered;

/**
 * <p> 脱敏切面
 *
 * @author 【千殇】（【罗玉华】qianshang.luo@tuya.com）
 * @since 2021/8/26 10:37 下午
 */
public class SensitiveAspect implements MethodInterceptor, PriorityOrdered {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveAspect.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object returnValue = methodInvocation.proceed();
        // 判断 是否有查看脱敏数据的权限
        if (this.isNeedDesensitize()) {
            SensitiveAspectSupport.sensitive(methodInvocation, returnValue);
        }
        return returnValue;
    }

    /**
     * <p> 判断是否需要脱敏
     *
     * @return boolean
     * @author [千殇] ([罗玉华]qianshang.luo@tuya.com)
     * @date 2021/9/8 3:41 下午
     */
    private boolean isNeedDesensitize() {
        return true;
    }

    @Override
    public int getOrder() {
        return 10;
    }
}