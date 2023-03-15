package com.zzp.learn.springboot.aop1.aggregate;

import com.zzp.learn.springboot.aop1.ISiteManagerBusinessApiService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
public class Aggregate {

    @Resource
    private ISiteManagerBusinessApiService siteManagerBusinessApiService;

    private String phone;

    public Aggregate(String phone) {
        this.phone = phone;
    }

    @Transactional
    public void log() {
        log.info("siteManagerBusinessApiService:{}", siteManagerBusinessApiService);
    }
}
