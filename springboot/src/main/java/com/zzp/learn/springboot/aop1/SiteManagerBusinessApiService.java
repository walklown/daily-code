package com.zzp.learn.springboot.aop1;

import com.zzp.learn.springboot.aop1.sensitive.Sensitive;
import com.zzp.learn.springboot.aop1.sensitive.Sensitive2;
import com.zzp.learn.springboot.aop1.sensitive.SensitiveClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author 【瑞尔】（【辛润峰】【ruier.xin@tuya.com】）
 * @description 场地管理
 * @since 2021/8/20 9:17 下午
 */
@Service
@SensitiveClass
public class SiteManagerBusinessApiService implements ISiteManagerBusinessApiService {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SiteManagerBusinessApiService.class);

    public SiteManagerBusinessApiService() {
        LOGGER.info("init");
    }

    @Override
    @Sensitive()
    @Sensitive2()
    public void getSite(String blockId, String siteId) {
        return;
    }

    @Override
    public void getSite1(String blockId, String siteId) {
        return;
    }
}
