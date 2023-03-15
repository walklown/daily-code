package com.walklown.attempt.dubbo.provider;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.ListenableFilter;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Activate(group = org.apache.dubbo.common.constants.CommonConstants.PROVIDER, order = 450)
public class AtopServiceResultFilter extends ListenableFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtopServiceResultFilter.class);

    public AtopServiceResultFilter() {
        super.listener = new AtopServiceResultFilter.ExceptionListener();
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    static class ExceptionListener implements Listener {

        @Override
        public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        }

        @Override
        public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {
        }
    }

}
