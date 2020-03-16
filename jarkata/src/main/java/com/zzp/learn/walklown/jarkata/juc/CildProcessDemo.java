package com.zzp.learn.walklown.jarkata.juc;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * AQS 类加载 线程池
 * @author shoujing
 * @date 2020/2/10 18:02
 */
public class CildProcessDemo {

    public static void main(String[] args) throws IOException {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        if (operatingSystemMXBean.getName().startsWith("Windows")) {
            Runtime.getRuntime().exec("calc");
        }
    }
}
