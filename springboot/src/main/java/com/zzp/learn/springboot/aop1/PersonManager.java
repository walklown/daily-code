package com.zzp.learn.springboot.aop1;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PersonManager {

    @Resource
    private ISiteManagerBusinessApiService siteManagerBusinessApiService;

    public ISiteManagerBusinessApiService getSiteManagerBusinessApiService() {
        return siteManagerBusinessApiService;
    }

    public void setSiteManagerBusinessApiService(ISiteManagerBusinessApiService siteManagerBusinessApiService) {
        this.siteManagerBusinessApiService = siteManagerBusinessApiService;
    }

    public PersonManager() {
        System.out.println("test");
    }
}
