package com.zzp.learn.springboot.aop1.configuration;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * ApplicationInitConfig
 * <br>
 *
 * @author 乐语(Patrick)-chenzp@tuya.com
 * @date 2020/6/11 14:17
 *       To change this template use Appearance | Editor | File and Code
 *       Templates.
 */
@Component
public class ApplicationInitConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        //应付apollo初始化
        System.setProperty("app.id", "tianlu");
    }

}
