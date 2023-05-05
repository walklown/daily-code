package com.walklown.learn.jarkata.base.reflect;

import org.apache.commons.lang3.time.StopWatch;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * 基于 MethodHandle 的反射
 * Why MethodHandle is slow than Reflection in JDK 1.8?
 * https://stackoverflow.com/questions/63066493/why-methodhandle-is-slow-than-reflection-in-jdk-1-8
 *
 * @author walklown
 */
public class InvokeReflectCostTest1 {

    /**
     *
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        MethodType con2Mt = MethodType.methodType(void.class, String.class, String.class);
        MethodHandle con2Mh = lookup.findConstructor(Person.class, con2Mt);
        Object target2 = con2Mh.invoke("zhang", "12133445566");

        //调用非private实例方法
        Method learnMethod = Person.class.getDeclaredMethod("getName");
        learnMethod.setAccessible(true);
        MethodHandle learnProMh = lookup.unreflect(learnMethod);
        StopWatch stopWatch = StopWatch.createStarted();
        for (int i = 0; i < 10000000; i++) {
            String name = (String) learnProMh.invoke(target2);
//            System.out.println(name);
        }
        stopWatch.stop();
        System.out.println("stopWatch:" + stopWatch);
    }
}
