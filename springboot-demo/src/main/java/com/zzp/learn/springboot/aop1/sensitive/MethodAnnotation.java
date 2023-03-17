package com.zzp.learn.springboot.aop1.sensitive;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法标记注解
 *
 * @author Walklown
 * @date 2023-03-16
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
@Repeatable(MethodAnnotations.class)
public @interface MethodAnnotation {

    String value() default "";
}
