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

package com.walklown.attempt.dubbo.consumer;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LogbackServiceProvider;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;
import com.walklown.attempt.dubbo.consumer.impl.ConsumerService1;
import com.walklown.attempt.dubbo.provider.ProviderDemo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@EnableDubbo
public class ConsumerDemo {

    public static final Logger LOGGER = LoggerFactory.getLogger(ConsumerDemo.class);
    public static void main(String[] args) throws InterruptedException {
        ch.qos.logback.classic.Logger rootLogger
                = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        ConsoleAppender<ILoggingEvent> appender = (ConsoleAppender) rootLogger.getAppender("STDOUT");

        ConfigurableApplicationContext context = SpringApplication.run(ConsumerDemo.class, args);
        LOGGER.info("dubbo service started");

        ConsumerService1 demoService = context.getBean("consumerService1", ConsumerService1.class);
        String hello = demoService.sayHello("world");
        System.out.println(hello);
        try {
            demoService.sayHelloEx("world");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new CountDownLatch(1).await();
    }
}
