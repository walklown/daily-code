package com.zzp.learn.springboot.ddd;

import com.zzp.learn.springboot.ddd.aggregate.Aggregate;
import com.zzp.learn.springboot.ddd.aggregate.CustomBeanFactory;
import com.zzp.learn.springboot.ddd.aggregate.IAggregate;
import com.zzp.learn.springboot.ddd.sensitive.ClassAnnotation;
import com.zzp.learn.springboot.ddd.sensitive.MethodAnnotation;
import com.zzp.learn.springboot.ddd.sensitive.MethodAnnotation1;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("aggregate")
    private Aggregate aggregate;

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
        IAggregate aggregate1 = customBeanFactory.wire();
        log.info("bean1:{}", aggregate1);

        IAggregate aggregate2 = customBeanFactory.wire();
        log.info("bean2:{}", aggregate2);

        log.info("bean:{}", aggregate);
    }
}
