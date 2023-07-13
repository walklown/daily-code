package com.walklown.attempt.dubbo.consumer.impl;

import org.apache.dubbo.rpc.Invocation;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class ReferenceHandlerCache {

    /**
     * 缓存传递语言执行方法
     */
    public static final ConcurrentHashMap<Method, Consumer<Invocation>> INVOKER_RUNNABLE_MAP = new ConcurrentHashMap<>();
}
