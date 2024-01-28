package com.walklown.attempt.client.zone;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration(proxyBeanMethods = false)
//@ConditionalOnClass(LoadBalancerClientFactory.class)
public class MyHttpClientConfiguration {

//    @Autowired
//    private LoadBalancerClientFactory loadBalancerClientFactory;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        loadBalancerClientFactory.setConfigurations(Collections.singletonList(new LoadBalancerClientSpecification("default.my-http-client", new Class[]{HttpClientConfiguration.class})));
//    }
}
