package com.walklown.learn.jarkata.base.reflect;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 基于 MethodHandle 的反射
 * Java Reflection, but much faster
 * https://www.optaplanner.org/blog/2018/01/09/JavaReflectionButMuchFaster.html
 *
 * @author walklown
 */
@State(Scope.Thread)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 7)
public class InvokeReflectCostTest {

    private static MethodHandle staticLearnProMh;

    private static MethodHandle staticPublicLearnProMh;

    private static Person person = new Person("zhang", "12345678");

    static {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
        try {
            Method learnMethod = Person.class.getDeclaredMethod("getMobile");
//        learnMethod.setAccessible(true);
            staticLearnProMh = lookup.unreflect(learnMethod);
            staticPublicLearnProMh = publicLookup.unreflect(learnMethod);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Benchmark
    public void direct(Blackhole bh) throws Throwable {
        bh.consume(person.name);
    }

    @Benchmark
    public void directGetter(Blackhole bh) throws Throwable {
        // 调用getter方法经过编译器优化，会优化为直接引用字段
        bh.consume(person.getMobile());
    }

    @Benchmark
    public void reflectGetter(Blackhole bh) throws Throwable {
        Method m = Person.class.getDeclaredMethod("getMobile");
        // getName是public的，isAccessible是false
        String name = (String) m.invoke(person);
        bh.consume(name);
    }

    @Benchmark
    public void reflectGetterAccessible(Blackhole bh) throws Throwable {
        Method m = Person.class.getDeclaredMethod("getMobile");
        m.setAccessible(true);
        String name = (String) m.invoke(person);
        bh.consume(name);
    }

    @Benchmark
    public void publicLookUp(Blackhole bh) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        // public方法
        MethodType getterMt = MethodType.methodType(String.class);
        MethodHandle getterMh = lookup.findVirtual(Person.class, "getMobile", getterMt);
        String name = (String) getterMh.invoke(person);
        bh.consume(name);
    }

    @Benchmark
    public void publicLookUpAccessible(Blackhole bh) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        // private方法，需要反射获取Method，设置accessible
        Method learnMethod = Person.class.getDeclaredMethod("getName");
        learnMethod.setAccessible(true);

        MethodHandle learnProMh = lookup.unreflect(learnMethod);
        String name = (String) learnProMh.invoke(person);
        bh.consume(name);
    }

    @Benchmark
    public void lookUp(Blackhole bh) throws Throwable {
        // lookup可直接访问私有属性，跳过访问限制校验，但速度却并不快
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        // public方法
        MethodType getterMt = MethodType.methodType(String.class);
        MethodHandle getterMh = lookup.findVirtual(Person.class, "getName", getterMt);
        String name = (String) getterMh.invoke(person);
        bh.consume(name);
    }

    /**
     * 使用静态
     *
     * @param bh 输出黑洞
     * @throws Throwable 异常
     */
    @Benchmark
    public void staticLookUp(Blackhole bh) throws Throwable {
        // static setAccessible后private属性
        String name = (String) staticLearnProMh.invoke(person);
        bh.consume(name);
    }

    /**
     * 使用静态
     *
     * @param bh 输出黑洞
     * @throws Throwable 异常
     */
    @Benchmark
    public void staticPublicLookUp(Blackhole bh) throws Throwable {
        // static setAccessible后private属性
        String name = (String) staticPublicLearnProMh.invoke(person);
        bh.consume(name);
    }
}
