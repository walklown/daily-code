/*
 *
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.walklown.attempt.dubbo.provider;

import com.walklown.attempt.dubbo.consumer.ConsumerDemo;
import com.walklown.attempt.dubbo.provider.impl.DemoService;
import com.walklown.attempt.dubbo.provider.impl.DemoServiceImpl;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

public class ProviderDemo {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProviderDemo.class);

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-provider.xml");
        context.start();
        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
//        startWithBootstrap();
    }
    private static void startWithBootstrap() {
        //前面的文章都在说这个服务配置对象的创建,中间又说了分层域模型,扩展加载机制
        ServiceConfig<DemoServiceImpl> service = new ServiceConfig<>();
        //为服务配置下服务接口和服务实现,下面两行用来初始化对象就不详细说了
        service.setInterface(DemoService.class);
        service.setRef(new DemoServiceImpl());

        ApplicationConfig applicationConfig = new ApplicationConfig("demo-provider");
        applicationConfig.setMetadataType("remote");
        //这一个篇章主要说这里:
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(applicationConfig)
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
                .protocol(new ProtocolConfig("injvm", 20880))
                .service(service)
                .start()
                .await();
    }
}
