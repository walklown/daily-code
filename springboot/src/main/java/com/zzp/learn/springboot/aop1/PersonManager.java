package com.zzp.learn.springboot.aop1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PersonManager {

    @Autowired
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
