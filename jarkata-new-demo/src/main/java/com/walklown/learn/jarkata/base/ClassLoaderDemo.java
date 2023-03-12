package com.walklown.learn.jarkata.base;

//import jdk.internal.loader.ClassLoaders;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * 与普通程序不同的是。Java程序（class文件）并不是本地的可执行程序。当运行Java程序时，首先运行JVM（Java虚拟机），然后再把Java class加载到JVM
 * 里运行，负责加载Java class的这部分就叫做Class Loader。 JVM本身包含了一个ClassLoader称为Bootstrap ClassLoader，和JVM一样，
 * BootstrapClassLoader是用本地代码实现的，它负责加载核心 JavaClass （即所有java.*开头的类）。另外JVM还会提供两个ClassLoader，它们都是用
 * Java语言编写的，由BootstrapClassLoader加载；其中 Extension ClassLoader 负责加载扩展的Javaclass（例如所有javax.*开头的类和存放在JRE
 * 的ext目录下的类），ApplicationClassLoader负责加载应用程序自身的类。
 *
 * @author shoujing
 * @date 2020/2/10 19:01
 */
public class ClassLoaderDemo {

//    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
////        URL url1=new URL("file:/classpath:/");
//        String[] pathElements = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
////        URL[] urls = Arrays.stream(pathElements).map(path -> {
////            try {
////                return new URL("file:/" + path);
////            } catch (MalformedURLException e) {
////                return null;
////            }
////        }).toArray(URL[]::new);
//        URL[] urls1 = new URL[]{new URL("file:D:\\workspace\\walklown\\attempt\\jarkata\\out\\production\\classes\\")};
//        URL[] urls = new URL[]{new URL("file:D:\\workspace\\walklown\\attempt\\netty\\out\\production\\classes\\")};
//        ClassLoader a1 = new URLClassLoader(urls1);
//        ClassLoader b1 = new URLClassLoader(urls1);
//        ClassLoader a = new URLClassLoader(urls);
//        ClassLoader b = new URLClassLoader(urls);
//        Class i0 = a.loadClass("com.zzp.learn.walklown.jarkata.base.ClassLoaderDemo");
//        Class i1 = b.loadClass("com.zzp.learn.walklown.jarkata.base.ClassLoaderDemo");
//        Class i2 = a.loadClass("com.zzp.learn.netty.blocking.BlockingIODemo");
//        Class i3 = b.loadClass("com.zzp.learn.netty.blocking.BlockingIODemo");
//        System.out.println(i0.equals(i1));;
//        System.out.println(i2.equals(i3));;
////        System.out.println(Class.forName("jdk.internal.loader.BuiltinClassLoader"));
//    }

    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
        System.out.println(Integer.class.getClassLoader());
    }
}
