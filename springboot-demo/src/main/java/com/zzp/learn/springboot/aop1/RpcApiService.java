package com.zzp.learn.springboot.aop1;

import com.zzp.learn.springboot.aop1.aggregate.Aggregate;
import com.zzp.learn.springboot.aop1.aggregate.CustomBeanFactory;
import com.zzp.learn.springboot.aop1.sensitive.ClassAnnotation;
import com.zzp.learn.springboot.aop1.sensitive.MethodAnnotation;
import com.zzp.learn.springboot.aop1.sensitive.MethodAnnotation1;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 【瑞尔】（【辛润峰】【ruier.xin@tuya.com】）
 * @description 场地管理
 * @since 2021/8/20 9:17 下午
 */
@Service
@ClassAnnotation
@Slf4j
public class RpcApiService implements IRpcApiService {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcApiService.class);

    @Autowired
    private CustomBeanFactory customBeanFactory;

    @Override
    @MethodAnnotation()
    @MethodAnnotation1()
    public void get(String name) {
        return;
    }

    @MethodAnnotation(value = "1")
//    @Transactional
    @Override
    public void get1(String name) {
        Aggregate aggregate = customBeanFactory.wire("123");
        log.info("bean:{}", aggregate);

        Aggregate aggregate1 = customBeanFactory.wire("456");
        log.info("bean1:{}", aggregate1);
    }
}
