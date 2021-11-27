
package com.zzp.learn.springboot.aop1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 * @author 守愚（张智沛）
 * @date 2021-01-07
 */
@SpringBootApplication(scanBasePackages = { "com.zzp.learn.springboot.aop1" }, exclude = {
        DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,
        ActiveMQAutoConfiguration.class, RedisAutoConfiguration.class, KafkaAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class })
@EnableAsync
@EnableScheduling
@EnableRetry
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ImportResource(locations = { "classpath:spring-config.xml" })
public class StartApplication {

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/zhangzhipei/workspace");
        ConfigurableApplicationContext applicationContext = SpringApplication.run(StartApplication.class, args);
       SiteManagerBusinessApiService
                siteManagerBusinessApiService = applicationContext.getBean(SiteManagerBusinessApiService.class);
        siteManagerBusinessApiService.getSite("", "");
        siteManagerBusinessApiService.getSite1("", "");
    }

}
