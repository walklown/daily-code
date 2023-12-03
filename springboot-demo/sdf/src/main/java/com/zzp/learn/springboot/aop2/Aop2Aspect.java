package springboot//package com.zzp.learn.springboot.aop2;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class Aop2Aspect {
//
//    @Before("@within(com.zzp.learn.springboot.aop2.A1)")
//    public void execute1() {
//        System.out.println("@within(com.zzp.learn.springboot.aop2.A1)");
//    }
//
//    @Before("@within(com.zzp.learn.springboot.aop2.A2)")
//    public void execute2() {
//        System.out.println("@within(com.zzp.learn.springboot.aop2.A2)");
//    }
//
//    @Pointcut("@target(com.zzp.learn.springboot.aop2.A1)")
//    public void pointcut1() {
//    }
//
//    @Before("pointcut1()")
//    public void execute3() {
//        System.out.println("@target(com.zzp.learn.springboot.aop2.A1)");
//    }
//
////    @Before("@target(com.zzp.learn.springboot.aop2.A2)")
////    public void execute4() {
////        System.out.println("@target(com.zzp.learn.springboot.aop2.A2)");
////    }
//}
