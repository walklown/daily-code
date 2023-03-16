package com.zzp.learn.springboot.aop1.aggregate;

import com.zzp.learn.springboot.aop1.IRpcApiService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
public class Aggregate implements InitializingBean {

    @Resource
    private IRpcApiService siteManagerBusinessApiService;

    private String phone;

    public Aggregate(String phone) {
        this.phone = phone;
    }

    public void log() {
        log.info("siteManagerBusinessApiService:{}", siteManagerBusinessApiService);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet");
    }
}
