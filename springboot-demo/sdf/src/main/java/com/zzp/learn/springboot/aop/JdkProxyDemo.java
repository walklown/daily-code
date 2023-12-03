package springboot;

import com.zzp.learn.springboot.aop.impl.Sleepable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 每次生成动态代理类对象时,实现了InvocationHandler接口的调用处理器对象
public class JdkProxyDemo implements InvocationHandler {
//    private Object target;// 这其实业务实现类对象，用来调用具体的业务方法

    // 通过构造函数传入目标对象
//    public JdkProxyDemo(Object target) {
//        this.target = target;
//    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用开始处理");
//        Object result = method.invoke(target, args);
        System.out.println("调用结束处理");
//        return result;
        return null;
    }

    public static void main(String[] args) throws SecurityException, IllegalArgumentException {
        // 被代理对象
        // 不要实现类的话
        // Exception in thread "main" java.lang.ClassCastException: class com.sun.proxy.$Proxy0 cannot be cast to class com.zzp.learn.springboot.aop.impl.Sleepable (com.sun.proxy.$Proxy0 and com.zzp.learn.springboot.aop.impl.Sleepable are in unnamed module of loader 'app')
//        Sleepable userDao = new Me();
        JdkProxyDemo jdkProxyDemo = new JdkProxyDemo();
        ClassLoader loader = Sleepable.class.getClassLoader();
//        Class<?>[] interfaces = userDao.getClass().getInterfaces();
        // 主要装载器、一组接口及调用处理动态代理实例
        Sleepable newProxyInstance = (Sleepable) Proxy.newProxyInstance(loader, new Class[]{Sleepable.class}, jdkProxyDemo);
        newProxyInstance.sleep();
    }
}
