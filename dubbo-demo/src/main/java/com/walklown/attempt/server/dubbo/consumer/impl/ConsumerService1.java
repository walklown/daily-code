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

package com.walklown.attempt.server.dubbo.consumer.impl;

import com.walklown.attempt.server.dubbo.consumer.ConsumerDemo;
import com.walklown.attempt.server.dubbo.provider.impl.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ConsumerService1 {

    public static final Logger LOGGER = LoggerFactory.getLogger(ConsumerDemo.class);

    @DubboReference(check = false, timeout = 2000)
    private DemoService demoService;

    public String sayHello(String name) {
        return demoService.sayHello(name);
    }

    public String sayHelloAsync(String name) {
        return demoService.sayHelloAsync(name);
    }

    public String sayHelloAsync1(String name) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = demoService.sayHelloAsync1(name);
        return future.get();
    }

    public String sayHelloEx(String name) {
        return demoService.sayHelloThrEx(name);
    }
}
