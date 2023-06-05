package com.walklown.learn.jarkata.base.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * https://www.optaplanner.org/blog/2018/01/09/JavaReflectionButMuchFaster.html
 * 性能测试 com.walklown.learn.jarkata.base.reflect.InvokeReflectCostTest
 *
 * @author walklown
 */
public class InvokeReflectDemo {

    public static void main(String[] args) throws Throwable {
        // 获取 Lookup
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
        // 构建方法类型描述符，描述参数与结果集类型
        MethodType con1Mt = MethodType.methodType(void.class, String.class, String.class);
        // 获取方法句柄
        MethodHandle con1Mh = publicLookup.findConstructor(Person.class, con1Mt);
        // 直接执行方法
        Object target1 = con1Mh.invoke("zhang", "12133445566");
        System.out.println("init:" + target1);

        // 调用public方法
        MethodType getterMt = MethodType.methodType(String.class);
        MethodHandle publicGetterMh = lookup.findVirtual(Person.class, "getName", getterMt);
        System.out.println(publicGetterMh.invoke(target1));
        // 调用private方法，实际上无访问权限，会报错
        try {
            MethodHandle privateGetterMh = lookup.findVirtual(Person.class, "privateGetMobile", getterMt);
            System.out.println(privateGetterMh.invoke(target1));
        } catch (Exception e) {
            System.out.println("调用private方法，实际上无访问权限，会报错:" + e.getMessage());
        }
        // 调用protect方法
        MethodHandle protectGetterMh = lookup.findVirtual(Person.class, "getMobile", getterMt);
        System.out.println(protectGetterMh.invoke(target1));
        // publicLookUp调用protect方法，即使有访问权限，非public也会报错
        try {
            MethodHandle protectGetterMhOfPublicLookUp = publicLookup.findVirtual(Person.class, "getMobile", getterMt);
            System.out.println(protectGetterMhOfPublicLookUp.invoke(target1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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
        MethodHandle nameMh = lookup.findGetter(Person.class, "name", String.class);
        System.out.println((String) nameMh.invoke(target1));
        //访问private的属性，需要借助反射
        Field nameField = Person.class.getDeclaredField("mobile");
        nameField.setAccessible(true);
        MethodHandle nameFromRefMh = lookup.unreflectGetter(nameField);
        System.out.println((String) nameFromRefMh.invoke(target1));
        //增强MethodHandle
        MethodType setterMt = MethodType.methodType(void.class, String.class);
        MethodHandle setterMh = lookup.findVirtual(Person.class, "setName", setterMt);
        MethodHandle bindedSetterMh = setterMh.bindTo(target1);
        bindedSetterMh.invoke("love CuiHuaNiu");
    }
}
