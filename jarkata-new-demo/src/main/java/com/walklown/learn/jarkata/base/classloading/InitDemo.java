package com.walklown.learn.jarkata.base.classloading;


/**
 *
 * @author walklown
 * @date 2019/10/9 16:00
 */
public class InitDemo {
 
	/**
	 * 主动引用引起类的初始化的第四种情况就是运行Test1的main方法时
	 * 导致Test1初始化，这一点很好理解，就不特别演示了。
	 * 本代码演示了前三种情况，以下代码都会引起InitClass的初始化，
	 * 但由于初始化只会进行一次，运行时请将注解去掉，依次运行查看结果。
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
	//  主动引用引起类的初始化一: new对象、读取或设置类的静态变量、调用类的静态方法。
//		new InitClass();
//		InitClass.a = "";
//		String a = InitClass.a;
//		InitClass.method();
		InitClass.method();Sfc sfc = ClassLoadingOrderDemo.getSfc();
		
	//  主动引用引起类的初始化二：通过反射实例化对象、读取或设置类的静态变量、调用类的静态方法。
	//	Class cls = InitClass.class;
	//	cls.newInstance();
		
	//	Field f = cls.getDeclaredField("a");
	//	f.get(null);
	//	f.set(null, "s");
	
	//	Method md = cls.getDeclaredMethod("method");
	//	md.invoke(null, null);
			
	//  主动引用引起类的初始化三：实例化子类，引起父类初始化。
	//	new SubInitClass();
 
	}
}