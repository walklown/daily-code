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

package com.walklown.attempt.dubbo.consumer.impl;

import com.walklown.attempt.dubbo.consumer.ConsumerDemo;
import com.walklown.attempt.dubbo.provider.impl.DemoService;
import jakarta.annotation.PostConstruct;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.ReferenceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService1 {

    public static final Logger LOGGER = LoggerFactory.getLogger(ConsumerDemo.class);

    @DubboReference(check = false, timeout = 2000)
    private DemoService demoService;

    @Autowired
    private List<ReferenceBean<?>> referenceBeanList;

    @PostConstruct
    public void initMethod() {
        System.out.println(referenceBeanList);
    }

    public String sayHello(String name) {
        return demoService.sayHello(name);
    }

    public String sayHelloEx(String name) {
        return demoService.sayHelloThrEx(name);
    }
}
