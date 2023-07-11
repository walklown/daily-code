package com.walklown.attempt.dubbo.consumer.impl;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.InvokerListener;
import org.apache.dubbo.rpc.RpcException;

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
            ReferenceHandlerCache.INVOKER_RUNNABLE_MAP.put(invoker.getInterface(), invocation -> {
                for (int i = 0; i < invocation.getArguments().length; i++) {
                    if (invocation.getArguments()[i] instanceof String) {
                        invocation.getArguments()[i] = "adapt";
                    }
                }
            });
        }
    }

    @Override
    public void destroyed(Invoker<?> invoker) {
        invokerList.remove(invoker);
    }
}
