package com.zzp.learn.walklown.jarkata.juc;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class CildProcessDemo {

    public static void main(String[] args) throws IOException {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        if (operatingSystemMXBean.getName().startsWith("Windows")) {
            Runtime.getRuntime().exec("calc");
        }
    }
}
