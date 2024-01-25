
package com.walklown.attempt.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 启动类
 *
 * @author Walklown
 * @date 2021-01-07
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableWebMvc
public class StartApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(StartApplication.class)
                .run(args);
    }
}
