package com.zzp.learn.walklown.jarkata.pattern.observice.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 事件的注册和监听
 * Created by Tom on 2018/3/17.
 */
public class EventLisenter {

    //Map相当于是一个注册器
    protected Map<String, Event> events = new HashMap<String, Event>();

    public void addLisenter(String eventType, Object target, Method callback) {
        //注册事件
        //用反射调用这个方法
        events.put(eventType, new Event(target, callback));
    }

    private void trigger(Event e) {
        e.setSource(this);
        e.setTime(System.currentTimeMillis());

        try {
            e.getCallback().invoke(e.getTarget(), e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


    protected void trigger(String call) {
        if (!this.events.containsKey(call)) {
            return;
        }
        trigger(this.events.get(call).setTrigger(call.toString()));
    }


}
