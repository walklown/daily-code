package com.walklown.learn.jarkata.base.reflect;

import org.apache.commons.lang3.time.StopWatch;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * 基于 MethodHandle 的反射
 * Java Reflection, but much faster
 * https://www.optaplanner.org/blog/2018/01/09/JavaReflectionButMuchFaster.html
 *
 * @author walklown
 */
public class InvokeReflectCostTest2 {

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.publicLookup();

        MethodType con2Mt = MethodType.methodType(void.class, String.class, String.class);
        MethodHandle con2Mh = lookup.findConstructor(Person.class, con2Mt);
        Object target2 = con2Mh.invoke("zhang", "12133445566");

        Method learnMethod = Person.class.getDeclaredMethod("getMobile");
//        learnMethod.setAccessible(true);
        MethodHandle learnProMh = lookup.unreflect(learnMethod);
        StopWatch stopWatch = StopWatch.createStarted();
        for (int i = 0; i < 10000000; i++) {
            String name = (String) learnProMh.invoke(target2);
//            System.out.println(name);
        }
        stopWatch.stop();
        System.out.println("stopWatch:" + stopWatch);

        learnMethod.setAccessible(true);
        stopWatch.reset();
        stopWatch.start();
        for (int i = 0; i < 10000000; i++) {
            String name = (String) learnProMh.invoke(target2);
//            System.out.println(name);
        }
        stopWatch.stop();
        System.out.println("stopWatch:" + stopWatch);

        Method learnMethod1 = Person.class.getDeclaredMethod("getName");
        learnMethod1.setAccessible(true);
        stopWatch.reset();
        stopWatch.start();
        for (int i = 0; i < 10000000; i++) {
            String name = (String) learnMethod1.invoke(target2);
//            System.out.println(name);
        }
        stopWatch.stop();
        System.out.println("stopWatch:" + stopWatch);
    }
}
