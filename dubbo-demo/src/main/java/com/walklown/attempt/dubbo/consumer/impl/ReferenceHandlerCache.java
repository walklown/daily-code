package com.walklown.attempt.dubbo.consumer.impl;

import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ReferenceHandlerCache {

    public static final Map<Class<?>, Consumer<Invocation>> INVOKER_RUNNABLE_MAP = new HashMap<>();
}
