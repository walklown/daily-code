package com.zzp.learn.springboot.aop1.sensitive;


import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * <p> 处理脱敏的切面
 *
 * @author 【千殇】（【罗玉华】qianshang.luo@tuya.com）
 * @since 2021/8/26 10:38 下午
 */
@Component
public class SensitiveAspectSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveAspectSupport.class);


    public static void sensitive(MethodInvocation methodInvocation, Object returnValue) {
        Long methodStartTime = System.currentTimeMillis();
        try {
            //获取方法上的注解
            Sensitive sensitive = SensitiveAspectSupport.getAnnotation(methodInvocation, Sensitive.class);
            if (null != sensitive) {
                if (returnValue instanceof Collection) {
                    // 处理集合
                    SensitiveUtils.supportList((List<? extends Object>) returnValue);
                } else {
                    SensitiveHandler.handle(returnValue);
                }
            }
        } catch (Exception e) {
            LOGGER.warn("处理动态数据脱敏 出现异常", e);
        }

        Long methodEndTime = System.currentTimeMillis();
        LOGGER.info("方法耗时:{} 秒", (methodEndTime - methodStartTime) / 1000F);
    }

    /**
     * 获取注解信息，兼容jdk与cglib代理，注解在实现类或者父类情况
     *
     * @param methodInvocation 切面参数
     * @param annotationClass  注解类
     * @return 注解信息
     * @date 2021-10-21
     * @author 守愚（张智沛）
     */
    public static <T extends Annotation> T getAnnotation(MethodInvocation methodInvocation, Class<T> annotationClass) {
        Method realMethod = null;
        try {
            realMethod =
                    methodInvocation.getThis().getClass()
                            .getMethod(methodInvocation.getMethod().getName(), methodInvocation.getMethod().getParameterTypes());
        } catch (NoSuchMethodException e) {
            LOGGER.info("Not found method", e);
            return null;
        }
        return AnnotationUtils.findAnnotation(realMethod, annotationClass);
    }
}
