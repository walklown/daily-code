package com.zzp.learn.walklown.jarkata.base;

import java.util.function.Function;

/**
 * 当编译器发现我们在匿名内部类中使用到了外部值的时候会自动为匿名内部类添加构造器用于将外部的值传递进去
 * 
 * 无法通过编译的代码
 *
 *     int a = 4;
 *     Function<Integer, Integer> f = new Function<Integer, Integer>() {
 *         @Override
 *         public Integer apply(Integer x) {
 *             return x + a;
 *         }
 *     };
 *     a = 12;
 *     System.out.println(f.apply(2));
 *
 * 假设这段代码能够编译，按照我们的直观理解，最后输出的应该是 14（因为 f.apply 得到的是 a+x，x 传入的是 2，而 a 被我们更改成了 12）
 * 我们回过头看上面的构造器传递值，实际上匿名内部类中得到的值是一份拷贝，那么匿名内部类中的 a 和外部的 a 实际上是两个不同地址里的值，它们不过是值
 * 相等罢了。那么，如果以上代码得到编译，实际上得到的输出应该为 6，因为 a 为 4 的时候被当作构造参数传进去了，后面更改 a 不会对它内部造成影响。这
 * 样的结果是不符合人的直观感觉的，容易造成混乱，故设计成必须由 final 修饰。
 * 除了以上原因之外，实际上有一个语法语义上的设计原因。匿名内部类可以看作是 java 早期对函数式的一种复杂化实现。以函数式编程的观点来看，能够使用所
 * 处环境的外部的值的函数被称为闭包。因此，以上的匿名内部类对象 f 是一个闭包，其所使用的值 a 来自于外部，被称为自由变量，在函数式编程的语义上，自
 * 由变量是不可变的。因此设计成 final 更加合理。
 *
 * @author shoujing
 * @date 2020/1/17 11:57
 */
public class FunctionDemo {

    public static void main(String[] args) {
        int a = 4;
        Function<Integer, Integer> f = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x + a;
            }
        };
        System.out.println(f.apply(2));
    }
}
