
package com.zzp.learn.springboot.aop1;

import com.zzp.learn.springboot.aop1.aggregate.CustomBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 * AopConfigUtils 设置了AspectJ的代理在order中优先级最高
 *
 * @author 守愚（张智沛）
 * @date 2021-01-07
 */
@SpringBootApplication(scanBasePackages = {"com.zzp.learn.springboot.aop1"}, exclude = {
        JmsAutoConfiguration.class, RedisAutoConfiguration.class, KafkaAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class})
@EnableAsync
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Slf4j
public class StartApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(StartApplication.class)
//                .web(WebApplicationType.NONE)
                .run(args);
        IRpcApiService
                serviceSite = applicationContext.getBean(IRpcApiService.class);

//        CustomBeanFactory customBeanFactory = applicationContext.getBean(CustomBeanFactory.class);

        Thread a = new Thread(()->{
            serviceSite.get1("");
        });
        a.start();
    }

}
