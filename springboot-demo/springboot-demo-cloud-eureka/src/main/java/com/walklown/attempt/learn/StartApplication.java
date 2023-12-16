
package com.walklown.attempt.learn;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 启动类
 *
 * @author Walklown
 * @date 2021-01-07
 */
@SpringBootApplication
@EnableEurekaServer
public class StartApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(StartApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
