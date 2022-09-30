package com.zzp.learn.walklown;

import com.zzp.learn.walklown.json.JacksonUtils;
import org.json.JSONObject;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyMain {

    public static void main(String[] args) {
        String json = "{\"name\":\"g****tuo\",\"hostId\":\"0604\",\"areas\":{\"areaNum\":1,\"areaStatus\":4,\"points\":[1,2,3,4,5]},\"panelType\":0,\"points\":[{\"pointCid\":\"0604_2_101000100000\",\"point\":1,\"status\":3},{\"pointCid\":\"0604_2_101000200000\",\"point\":2,\"status\":3},{\"pointCid\":\"0604_2_101000300000\",\"point\":3,\"status\":3},{\"pointCid\":\"0604_2_101000400000\",\"point\":4,\"status\":3},{\"pointCid\":\"0604_2_101000500000\",\"point\":5,\"status\":3}]}";
        JSONObject jsonObject = JacksonUtils.parseObject(json);
        System.out.println(jsonObject);
    }
}
