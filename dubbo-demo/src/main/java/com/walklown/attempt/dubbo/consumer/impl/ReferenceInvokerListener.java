package com.walklown.attempt.dubbo.consumer.impl;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.InvokerListener;
import org.apache.dubbo.rpc.RpcException;

@Activate
public class ReferenceInvokerListener implements InvokerListener {

    @Override
    public void referred(Invoker<?> invoker) throws RpcException {
    }

    @Override
    public void destroyed(Invoker<?> invoker) {
    }
}
