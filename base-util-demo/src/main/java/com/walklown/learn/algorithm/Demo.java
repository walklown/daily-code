package com.walklown.learn.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        String str = "{\"data\":{\"deviceId\":\"6ca1099aaa582a2c972zop\",\"imageId\":\"f4190e0fac770c5f8b9bb1579d334d73\",\"picUrl\":\"https://tuya-temp-fileupload-1254153901.cos.ap-shanghai.myqcloud.com/qilin/estate_faceImg/1543802546869502051.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDeATXP2QaWDgwxjGYcS6TbT6VfbIkxvgE%26q-sign-time%3D1656906185%3B1656909785%26q-key-time%3D1656906185%3B1656909785%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Db9d2361ab750080334a791baac43aa15cfc0408c\",\"projectId\":\"1503924425332989952\",\"success\":1,\"t\":1656906185605,\"uid\":\"1531877943960141871\",\"way\":4},\"messageId\":\"3a74491313ca4569975188def6c552b8\",\"mode\":\"dc-userPass\"}";
        String[] arrays = str.split(",\n");
        Set<String> set = new HashSet<>(Arrays.asList(arrays));
        for (String s : set) {
            System.out.println(s);
        }
    }
}
