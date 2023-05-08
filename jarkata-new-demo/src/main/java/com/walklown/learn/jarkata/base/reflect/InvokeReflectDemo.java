package com.walklown.learn.jarkata.base.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * https://www.optaplanner.org/blog/2018/01/09/JavaReflectionButMuchFaster.html
 * 性能测试 {@link com.walklown.learn.jarkata.base.reflect.InvokeReflectCostTest2}
 *
 * @author walklown
 */
public class InvokeReflectDemo {

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();

        MethodType con1Mt = MethodType.methodType(void.class);
        MethodHandle con1Mh = publicLookup.findConstructor(Person.class, con1Mt);
        Object target1 = con1Mh.invoke();
        System.out.println(target1);

        MethodType con2Mt = MethodType.methodType(void.class, String.class, String.class);
        MethodHandle con2Mh = lookup.findConstructor(Person.class, con2Mt);
        Object target2 = con2Mh.invoke("zhang", "12133445566");
        System.out.println(target2);

        //调用非private实例方法
        MethodType getterMt = MethodType.methodType(String.class);
        MethodHandle getterMh = lookup.findVirtual(Person.class, "getName", getterMt);
        String name = (String) getterMh.invoke(target2);
        System.out.println(name);
        //访问private方法
        Method learnMethod = Person.class.getDeclaredMethod("sleep", String.class);
        learnMethod.setAccessible(true);
        MethodHandle learnProMh = lookup.unreflect(learnMethod);
        learnProMh.invoke(target1, "12h");
        //调用静态方法
        MethodType decMt = MethodType.methodType(String.class, String.class);
        MethodHandle decMh = lookup.findStatic(Person.class, "transfer", decMt);
        String dec = (String) decMh.invoke("abc");
        System.out.println(dec);
        //访问非private属性
        MethodHandle nameMh = lookup.findGetter(Person.class,"name", String.class);
        System.out.println((String) nameMh.invoke(con1Mh.invoke()));
        //访问private的属性，需要借助反射
        Field nameField = Person.class.getDeclaredField("mobile");
        nameField.setAccessible(true);
        MethodHandle nameFromRefMh = lookup.unreflectGetter(nameField);
        System.out.println((String) nameFromRefMh.invoke(target1));
        //增强MethodHandle
        MethodType setterMt = MethodType.methodType(void.class, String.class);
        MethodHandle setterMh = lookup.findVirtual(Person.class, "setName", setterMt);
        MethodHandle bindedSetterMh = setterMh.bindTo(target2);
        bindedSetterMh.invoke("love CuiHuaNiu");
        System.out.println((String) getterMh.invoke(target2));
    }
}
