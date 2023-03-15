package com.zzp.learn.walklown.jarkata.base.classloading;

/**
 *
 * @author walklown
 * @date 2019/10/9 16:03
 * -按照顺序自上而下运行类中的变量赋值语句和静态语句，如果有父类，则首先按照顺序运行父类中的变量赋值语句和静态语句。
 * -在类的初始化阶段，只会初始化与类相关的静态赋值语句和静态语句，也就是有static关键字修饰的信息，而没有static修饰
 * 的赋值语句和执行语句在实例化对象的时候才会运行。
 *
 * clinit方法：
 * .java文件在编译后会在字节码文件中生成clinit方法，该方法被称之为类构造器。该方法中的操作及其顺序为：
 * 1.父类静态变量初始化 2.父类静态语句块 3.子类静态变量初始化 4.子类静态语句块   （若父类为接口，则不会调用父类的clinit方法，一个类可以没有clinit方法）
 * init方法：
 * .Java文件在编译后会在字节码文件中生成init方法，该方法被称之为实例构造器。init方法是在对象实例化时执行的。该方法中的操作及其顺序为
 * 1.父类变量初始化  2.父类语句块  3.父类构造函数  4.子类变量初始化 5.子类语句块  6.子类构造函数
 * 1).<clinit>方法和类的构造函数不同，它不需要显示调用父类的构造方法，虚拟机会保证子类的<clinit>方法执行之前，父类的此方法已经执行完毕，因此虚拟机中第一个被执行的<clinit>方法的类肯定是java.lang.Object
 * 2).接口中不能使用static块，但是接口仍然有变量初始化的操作，因此接口也会生成<clinit>方法。但接口和类不同的是，不会先去执行继承接口的<clinit>方法，而是在调用父类变量的时候，才会去调用<clinit>方法。接口的实现类也是一样的。
 */
public class ClassLoadingOrderDemo {

    private String a;

    private static String b = "b";

    public static void main(String[] args) {
        System.out.printf("%d,%d\n%d,%d\n",
                SingleTon.getInstance().count1, SingleTon.getInstance().count2,
                SingleTon.count3, SingleTon.count4
        );
    }

    public static Sfc getSfc() {
        return new Sfc();
    }

    public class InnerClass {

        public InnerClass() {
            System.out.println(ClassLoadingOrderDemo.b);
            ClassLoadingOrderDemo demo = new ClassLoadingOrderDemo();
            System.out.println(demo.a);
        }

    }

    public static class StaticInnerClass {

        public static void log() {
            System.out.println(ClassLoadingOrderDemo.b);
            ClassLoadingOrderDemo demo = new ClassLoadingOrderDemo();
            System.out.println(demo.a);
        }
    }
}

class Sfc {

}

class SingleTon {
    private static SingleTon singleTon = new SingleTon();
    public int count1;
    public int count2 = 0;
    public static int count3;
    public static int count4 = 0;

    private SingleTon() {
        count1++;
        count2++;
        count3++;
        count4++;
    }

    public static SingleTon getInstance() {
        return singleTon;
    }
}
