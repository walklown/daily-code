package com.walklown.attempt.dubbo.consumer.impl;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.InvokerListener;
import org.apache.dubbo.rpc.RpcException;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@Activate
public class ReferenceInvokerListener implements InvokerListener {

    private Set<Invoker<?>> invokerList = new HashSet<>();

    @Override
    public void referred(Invoker<?> invoker) throws RpcException {
        invokerList.add(invoker);
        if (ReferenceHandlerCache.INVOKER_RUNNABLE_MAP.containsKey(invoker.getInterface())) {
            return;
        }
        if (invoker.getInterface().getPackageName().startsWith("com.walklown.attempt")) {
            initCache(invoker, String.class);
        }
    }

    private void initCache(Invoker<?> invoker, Class<?> requestClass) {
        for (Method declaredMethod : invoker.getInterface().getDeclaredMethods()) {
            for (int i = 0; i < declaredMethod.getParameterTypes().length; i++) {
                Class<?> currentClass = declaredMethod.getParameterTypes()[i];
                if (requestClass.equals(currentClass)) {
                    ReferenceHandlerCache.INVOKER_RUNNABLE_MAP.put(declaredMethod, System.out::println);
                    // 当前method已处理
                    break;
                }
                if (currentClass != null) {
                    // 匹配到
                    break;
                }
            }
        }
    }

    @Override
    public void destroyed(Invoker<?> invoker) {
        invokerList.remove(invoker);
    }
}
