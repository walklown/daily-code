package com.walklown.attempt.dubbo.provider.impl;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.ServiceListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Activate
public class MyServiceListener implements ServiceListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyServiceListener.class);

    @Override
    public void exported(ServiceConfig sc) {
        LOGGER.info("exported");
    }

    @Override
    public void unexported(ServiceConfig sc) {

    }
}
