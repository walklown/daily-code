package com.zzp.learn.walklown.jarkata.base.classloading;

/**
 *
 * @author walklown
 * @date 2019/10/9 16:03
 * -按照顺序自上而下运行类中的变量赋值语句和静态语句，如果有父类，则首先按照顺序运行父类中的变量赋值语句和静态语句。
 * -在类的初始化阶段，只会初始化与类相关的静态赋值语句和静态语句，也就是有static关键字修饰的信息，而没有static修饰
 * 的赋值语句和执行语句在实例化对象的时候才会运行。
 */
public class ClassLoadingOrderDemo {

    public static void main(String[] args) {
        System.out.printf("%d,%d\n%d,%d\n",
                SingleTon.getInstance().count1, SingleTon.getInstance().count2,
                SingleTon.count3, SingleTon.count4
        );
    }
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
