package com.walklown.attempt.client.zone;

import com.walklown.attempt.zone.HttpClientConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientSpecification;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(LoadBalancerClientFactory.class)
public class MyHttpClientConfiguration implements InitializingBean {

    @Autowired
    private LoadBalancerClientFactory loadBalancerClientFactory;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadBalancerClientFactory.setConfigurations(Collections.singletonList(new LoadBalancerClientSpecification("default.my-http-client", new Class[]{HttpClientConfiguration.class})));
    }
}
