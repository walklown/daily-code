package com.zzp.pattern;

public class SingleDemo {
    private static boolean initialized = false;
    private SingleDemo(){
        synchronized (SingleDemo.class){
            if (!initialized){
                initialized = !initialized;
            }else {
                throw new RuntimeException("单例已被初始化");
            }
        }
    }

    public static final SingleDemo getInstance(){ return LazyHolder.single;}

    private static class LazyHolder{
        private static final SingleDemo single = new SingleDemo();
    }
}
