package com.walklown.learn.jarkata.base.classloading;

class InitClass{
	static {
		System.out.println("初始化InitClass");
	}
	public static String a = null;
	public final static String b = "b";
	public static void method(){}
}