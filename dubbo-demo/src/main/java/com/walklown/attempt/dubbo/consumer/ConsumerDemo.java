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

import com.walklown.attempt.dubbo.consumer.impl.ConsumerService1;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication(scanBasePackages = {"com.walklown.attempt.dubbo.consumer"})
@EnableDubbo
@ImportResource(locations = {"classpath:spring/dubbo-consumer.xml"})
public class ConsumerDemo {

    public static final Logger LOGGER = LoggerFactory.getLogger(ConsumerDemo.class);
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(ConsumerDemo.class, args);
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-consumer.xml");
//        context.start();
        LOGGER.info("dubbo service started");

        ConsumerService1 demoService = context.getBean("consumerService1", ConsumerService1.class);
        String hello = demoService.sayHello("world");
        System.out.println(hello);
        try {
            hello = demoService.sayHelloEx("world");
            System.out.println(hello);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            hello = demoService.sayHelloAsync("world");
            System.out.println(hello);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            hello = demoService.sayHelloAsync1("world");
            System.out.println(hello);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
