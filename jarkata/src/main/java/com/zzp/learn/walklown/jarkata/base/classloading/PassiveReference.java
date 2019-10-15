package com.zzp.learn.walklown.jarkata.base.classloading;


 
/**
 *
 * @author walklown
 * @date 2019/10/9 16:07
 */
public class PassiveReference {
 
	public static void main(String[] args) throws Exception{
	//	String a = SubInitClass.a;// 引用父类的静态字段，只会引起父类初始化，而不会引起子类的初始化
	//	String b = InitClass.b;// 使用类的常量不会引起类的初始化
		SubInitClass[] sc = new SubInitClass[10];// 定义类数组不会引起类的初始化
	}
}