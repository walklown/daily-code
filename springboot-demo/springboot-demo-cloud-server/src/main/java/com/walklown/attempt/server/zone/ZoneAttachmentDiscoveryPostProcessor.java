package com.walklown.attempt.server.zone;

import com.netflix.appinfo.ApplicationInfoManager;
import io.microsphere.multiple.active.zone.ZoneAttachmentHandler;
import io.microsphere.multiple.active.zone.ZoneContext;
import io.microsphere.multiple.active.zone.eureka.ZoneAttachmentPreRegistrationHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cloud.configuration.TlsProperties;
import org.springframework.cloud.netflix.eureka.config.DiscoveryClientOptionalArgsConfiguration;
import org.springframework.cloud.netflix.eureka.http.EurekaClientHttpRequestFactorySupplier;
import org.springframework.cloud.netflix.eureka.http.RestTemplateDiscoveryClientOptionalArgs;
import org.springframework.stereotype.Component;

/**
 * {@link RestTemplateDiscoveryClientOptionalArgs} 注入 {@link ZoneAttachmentPreRegistrationHandler}，实现区域信息注册
 *
 * @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 * @see DiscoveryClientOptionalArgsConfiguration#restTemplateDiscoveryClientOptionalArgs(TlsProperties, EurekaClientHttpRequestFactorySupplier)
 */
@Component
public class ZoneAttachmentDiscoveryPostProcessor implements BeanPostProcessor {

    @Autowired
    private ApplicationInfoManager applicationInfoManager;

    @Autowired
    private ZoneContext zoneContext;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!RestTemplateDiscoveryClientOptionalArgs.class.equals(bean.getClass())) {
            return bean;
        }
        RestTemplateDiscoveryClientOptionalArgs args = ((RestTemplateDiscoveryClientOptionalArgs) bean);
        args.setPreRegistrationHandler(new ZoneAttachmentPreRegistrationHandler(applicationInfoManager,
                new ZoneAttachmentHandler(zoneContext)));
        return bean;
    }
}
