package com.walklown.attempt.dubbo.consumer.impl;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.ListenableFilter;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.function.Consumer;

@Activate(group = CommonConstants.CONSUMER, order = 450)
public class ReferenceFilter implements Filter, Filter.Listener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            Method method = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            Consumer<Invocation> consumer = ReferenceHandlerCache.INVOKER_RUNNABLE_MAP.get(method);
            if (consumer != null) {
                consumer.accept(invocation);
            }
        } catch (NoSuchMethodException e) {
            LOGGER.error("", e);
        }
        Result result = invoker.invoke(invocation);
        LOGGER.info("result:{}", result);
        return result;
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        LOGGER.info("onResponse");
    }

    @Override
    public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {
        LOGGER.info("onError");
    }
}
