
package com.walklown.attempt.client;

import com.walklown.attempt.client.zone.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
