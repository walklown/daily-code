package com.zzp.learn.springboot;

import com.zzp.learn.springboot.core.ErrorMonitorRegistrar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringbootApplication.class);
        ErrorMonitorRegistrar asel = new ErrorMonitorRegistrar();
        springApplication.addListeners(asel);
        ConfigurableApplicationContext context = springApplication.run(args);
    }
}

