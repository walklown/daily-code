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

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

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
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@Measurement(iterations = 3)
public class InvokeReflectCostTest {

    private static MethodHandle staticPublicMethodHandle;

    private MethodHandle protectMethodHandle;

    private MethodHandle publicMethodHandleOfPublicLookUp;

    private MethodHandle privateAccessiableGetMethodHandle;

    private Method publicGetMethod;

    private Method privateAccessiableGetMethod;

    private static final TestPerson person = new TestPerson("zhang", "12345678");

    @Setup(Level.Iteration)
    public void initMethod() {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();

        MethodType getterMt = MethodType.methodType(String.class);
        try {
            publicGetMethod = TestPerson.class.getDeclaredMethod("getName");
            privateAccessiableGetMethod = TestPerson.class.getDeclaredMethod("privateGetMobile");
            privateAccessiableGetMethod.setAccessible(true);
            publicMethodHandleOfPublicLookUp = publicLookup.unreflect(publicGetMethod);

            protectMethodHandle = lookup.findVirtual(TestPerson.class, "getMobile", getterMt);
            privateAccessiableGetMethodHandle = lookup.unreflect(privateAccessiableGetMethod);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    static {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
            Method learnMethod = TestPerson.class.getDeclaredMethod("getName");
            staticPublicMethodHandle = lookup.unreflect(learnMethod);
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
        bh.consume(person.getName());
    }

    @Benchmark
    public void reflectGetter(Blackhole bh) throws Throwable {
        // getName是public的，isAccessible是false
        String name = (String) publicGetMethod.invoke(person);
        bh.consume(name);
    }

    @Benchmark
    public void reflectGetterAccessible(Blackhole bh) throws Throwable {
        // getName是public的，isAccessible默认是false，设置为true仍然可以提高速度
        String name = (String) privateAccessiableGetMethod.invoke(person);
        bh.consume(name);
    }

    @Benchmark
    public void publicLookUp(Blackhole bh) throws Throwable {
        String name = (String) publicMethodHandleOfPublicLookUp.invoke(person);
        bh.consume(name);
    }

    @Benchmark
    public void lookUp(Blackhole bh) throws Throwable {
        // lookup可直接访问有访问权限的非public方法
        String name = (String) protectMethodHandle.invoke(person);
        bh.consume(name);
    }

    @Benchmark
    public void lookUpAccessible(Blackhole bh) throws Throwable {
        // lookup访问无访问权限的非public方法，同样要借助反射
        String name = (String) privateAccessiableGetMethodHandle.invoke(person);
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
        // lookup创建的static句柄访问
        String name = (String) staticPublicMethodHandle.invoke(person);
        bh.consume(name);
    }
}
