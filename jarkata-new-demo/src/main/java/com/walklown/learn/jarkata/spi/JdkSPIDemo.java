package com.walklown.learn.jarkata.spi;

import com.walklown.learn.jarkata.spi.jdk.Robot;

import java.util.ServiceLoader;

public class JdkSPIDemo {

    public static void main(String[] args) {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }
}
