
package com.zzp.learn.springboot.aop1;

import com.zzp.learn.springboot.aop1.aggregate.Aggregate;
import com.zzp.learn.springboot.aop1.aggregate.CustomBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.ServletContextAware;

/**
 * 启动类
 *
 * @author 守愚（张智沛）
 * @date 2021-01-07
 */
@SpringBootApplication(scanBasePackages = {"com.zzp.learn.springboot.aop1"}, exclude = {
//        DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,
        JmsAutoConfiguration.class, RedisAutoConfiguration.class, KafkaAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class})
@EnableAsync
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Slf4j
public class StartApplication {

    public static void main(String[] args) {
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/zhangzhipei/workspace");
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(StartApplication.class)
//                .web(WebApplicationType.NONE)
                .run(args);
//        SiteManagerBusinessApiService
//                siteManagerBusinessApiService = applicationContext.getBean(SiteManagerBusinessApiService.class);
        ISiteManagerBusinessApiService
                siteManagerBusinessApiService = applicationContext.getBean(ISiteManagerBusinessApiService.class);
//        siteManagerBusinessApiService.getSite("", "");
        siteManagerBusinessApiService.getSite1("", "");

//        ServletContextAwareTest servletContextAware = applicationContext.getBean(ServletContextAwareTest.class);
//        servletContextAware.getSiteManagerBusinessApiService();


        CustomBeanFactory customBeanFactory = applicationContext.getBean(CustomBeanFactory.class);
        Aggregate aggregate = customBeanFactory.wire("123");
        aggregate.log();
        log.info("bean:{}", aggregate);

        Aggregate aggregate1 = customBeanFactory.wire("456");
        aggregate1.log();
        log.info("bean1:{}", aggregate1);
    }

}
