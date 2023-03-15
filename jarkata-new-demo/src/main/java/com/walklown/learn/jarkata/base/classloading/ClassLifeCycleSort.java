package com.walklown.learn.jarkata.base.classloading;

/**
 * 类生命周期
 * @author walklown
 * @date 2019/10/9 15:45
 * 加载、(连接、初始化、使用)、和卸载
 * 一、加载：找到需要加载的类并把类的信息加载到jvm的方法区中，然后在堆区中实例化一个java.lang.Class对象，作为方法区中这个类的信息的入口
 *      --有时连接阶段并不会等加载阶段完全完成之后才开始，而是交叉进行，加载阶段总是在连接阶段之前开始，连接阶段总是在加载阶段完成之后完成
 * 二、连接：
 * -验证：当一个类被加载之后，必须要验证一下这个类是否合法，比如这个类是不是符合字节码的格式、变量与方法是不是有重复、数据类型是不是有效
 *  、继承与实现是否合乎标准等等。总之，这个阶段的目的就是保证加载的类是能够被jvm所运行。
 * -准备：准备阶段的工作就是为类的静态变量分配内存并设为jvm默认的初值，对于非静态的变量，则不会为它们分配内存。有一点需要注意，这时候，静态变量的初值为jvm默认的初值，而不是我们在程序中设定的初值。jvm默认的初值是这样的：
 *  1、基本类型（int、long、short、char、byte、boolean、float、double）的默认值为0。
 *  2、引用类型的默认值为null。
 *  3、常量的默认值为我们程序中设定的值，比如我们在程序中定义final static int a = 100，则准备阶段中a的初值就是100。
 * -解析：把常量池中的符号引用（方法签名？）转换为直接引用
 * 三、初始化：
 * {@link ClassLoadingOrderDemo}
 *      主动引用
 *      -通过new关键字实例化对象、读取或设置类的静态变量、调用类的静态方法。
 *      -通过反射方式执行以上三种行为。
 *      -初始化子类的时候，会触发父类的初始化。
 *      -作为程序入口直接运行时（也就是直接调用main方法
 *      {@link InitDemo}
 * 四、使用：
 * 使用阶段包括主动引用和被动引用，主动饮用会引起类的初始化，而被动引用不会引起类的初始化
 *      被动引用：{@link PassiveReference}
 *      -引用父类的静态字段，只会引起父类的初始化，而不会引起子类的初始化。
 *      -定义类数组，不会引起类的初始化。
 *      -引用类的常量，不会引起类的初始化
 * 五、卸载(方法区清楚类信息)
 * -该类所有的实例都已经被回收，也就是java堆中不存在该类的任何实例。
 * -加载该类的ClassLoader已经被回收。
 * -该类对应的java.lang.Class对象没有任何地方被引用，无法在任何地方通过反射访问该类的方法。
 */
public class ClassLifeCycleSort {

//    private static final Boolean LIVING = true;

    public static final ClassLifeCycleSort INIT_SORT = new ClassLifeCycleSort();

    private ClassLifeCycleSort() {

    }

    private static final Boolean LIVING = true;

    private final Boolean alive = LIVING;

    public final Boolean lives() {
        return alive;
    }

    //对象初始化顺序
    //类加载 -> 字段初始化 -> {}模块 -> 构造器

    public static void main(String[] args) {
        System.out.printf(INIT_SORT.alive ?
                "Hound Dog" : "Heartbreak Hotel");
    }
}
