package com.walklown.learn.jarkata.base.reflect;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 基于 MethodHandle 的反射
 * Java Reflection, but much faster
 * https://www.optaplanner.org/blog/2018/01/09/JavaReflectionButMuchFaster.html
 * for {@link InvokeReflectDemo}
 *
 * @author walklown
 */
@State(Scope.Thread)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@Measurement(iterations = 20)
public class InvokeReflectCostTest1 {

    private Function getterFunction;

    private Method privateAccessiableGetMethod;

    private static final TestPerson person = new TestPerson("zhang", "12345678");

    @Setup(Level.Iteration)
    public void initMethod() throws Throwable {
        privateAccessiableGetMethod = TestPerson.class.getDeclaredMethod("privateGetMobile");
        privateAccessiableGetMethod.setAccessible(true);

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        CallSite site = LambdaMetafactory.metafactory(lookup,
                "apply",
                MethodType.methodType(Function.class),
                MethodType.methodType(Object.class, Object.class),
                lookup.findVirtual(TestPerson.class, "getName", MethodType.methodType(String.class)),
                MethodType.methodType(String.class, TestPerson.class));
        getterFunction = (Function) site.getTarget().invokeExact();
    }

    @Benchmark
    public void direct(Blackhole bh) throws Throwable {
        bh.consume(person.name);
    }

    @Benchmark
    public void reflectGetterAccessible(Blackhole bh) throws Throwable {
        // getName是public的，isAccessible默认是false，设置为true仍然可以提高速度
        String name = (String) privateAccessiableGetMethod.invoke(person);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetaFactoryLookUp(Blackhole bh) throws Throwable {
        // lookup可直接访问有访问权限的非public方法
        String name = (String) getterFunction.apply(person);
        bh.consume(name);
    }

}
