package com.zzp.learn.springboot.aop1;

import com.zzp.learn.springboot.aop1.aggregate.Aggregate;
import com.zzp.learn.springboot.aop1.aggregate.CustomBeanFactory;
import com.zzp.learn.springboot.aop1.sensitive.Sensitive;
import com.zzp.learn.springboot.aop1.sensitive.Sensitive2;
import com.zzp.learn.springboot.aop1.sensitive.SensitiveClass;
import com.zzp.learn.springboot.aop1.sensitive.Sensitives;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;

import java.util.Map;

/**
 * @author 【瑞尔】（【辛润峰】【ruier.xin@tuya.com】）
 * @description 场地管理
 * @since 2021/8/20 9:17 下午
 */
@Service
@SensitiveClass
@Slf4j
public class SiteManagerBusinessApiService implements ISiteManagerBusinessApiService {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SiteManagerBusinessApiService.class);

    @Autowired
    private CustomBeanFactory customBeanFactory;

    @Autowired
    private PersonConfigurationProperties personConfigurationProperties;


    @Override
    @Sensitive()
    @Sensitive2()
    public void getSite(String blockId, String siteId) {
        return;
    }

//    @Sensitives({
//            @Sensitive()
//    })
    @Sensitive(value = "1")
    @Transactional
    @Override
    public void getSite1(String blockId, String siteId) {
        Aggregate aggregate = customBeanFactory.wire("123");
        log.info("bean:{}", aggregate);

        Aggregate aggregate1 = customBeanFactory.wire("456");
        log.info("bean1:{}", aggregate1);
    }
}
