package com.walklown.attempt.server.init;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 1、@MapperScan
 * 2、MapperScannerConfigurer
 * 3、{@link SpringApplication#personMapperBean(SqlSessionFactory)}
 * 4、{@link PersonMapperImpl}
 * 5、{@link PersonRepository#insert1(SqlSessionFactory)}
 */
@SpringBootApplication
//@MapperScan()
public class SpringApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
        System.out.println("dubbo service started");
    }

    @Bean
    public MapperFactoryBean<PersonMapper> personMapperBean(SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<PersonMapper> mapperFactoryBean = new MapperFactoryBean<>();
        mapperFactoryBean.setMapperInterface(PersonMapper.class);
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
        return mapperFactoryBean;
    }
}