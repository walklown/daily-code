package com.zzp.learn.springboot.schedular;

import com.zzp.learn.springboot.aop.AopBootstrap;
import com.zzp.learn.springboot.aop.impl.Sleepable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication(scanBasePackages = "com.zzp.learn.springboot.schedular")
@EnableScheduling
public class SchedularDemo {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SchedularDemo.class);
        ConfigurableApplicationContext context = springApplication.run(args);
    }

    @Scheduled(cron = "#{@crons}")
    public void excute() {
        System.out.println("SchedularDemo.excute");
    }

    @Bean
    public String crons() {
        return "*/5 * * * * *";
    }
}
