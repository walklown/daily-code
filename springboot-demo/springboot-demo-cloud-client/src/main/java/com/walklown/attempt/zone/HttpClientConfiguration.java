package com.walklown.attempt.zone;

import io.microsphere.multiple.active.zone.ZoneContext;
import io.microsphere.multiple.active.zone.ZonePreferenceFilter;
import io.microsphere.multiple.active.zone.cloud.CloudServerZoneResolver;
import io.microsphere.multiple.active.zone.spring.cloud.loadbalancer.ZonePreferenceServiceInstanceListSupplier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.ConditionalOnBlockingDiscoveryEnabled;
import org.springframework.cloud.client.ConditionalOnReactiveDiscoveryEnabled;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientSpecification;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.cloud.loadbalancer.support.LoadBalancerEnvironmentPropertyUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * http配置
 *
 * @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 * @see LoadBalancerClientConfiguration.BlockingSupportConfiguration
 */
@Configuration(proxyBeanMethods = false)
@LoadBalancerClients
//@Import(value = ZonePreferenceLoadBalancerClientConfiguration.class)
public class HttpClientConfiguration {

    @ConditionalOnReactiveDiscoveryEnabled
    @Order(193827465)
    static class ReactiveConfiguration {

        @Bean
        @ConditionalOnBean(DiscoveryClient.class)
        @ConditionalOnMissingBean
        @Conditional(MyZoneConfigurationCondition.class)
        public ServiceInstanceListSupplier zonePreferenceServiceInstanceListSupplier(
                ConfigurableApplicationContext context,
                ZonePreferenceFilter<ServiceInstance> zonePreferenceFilter) {
            return ServiceInstanceListSupplier.builder().withDiscoveryClient().with(
                    (ctx, delegate) -> new ZonePreferenceServiceInstanceListSupplier(delegate, zonePreferenceFilter)
            ).build(context);
        }
    }


    @ConditionalOnBlockingDiscoveryEnabled
    @Order(193827466)
    static class BlockingConfiguration {

        @Bean
        @ConditionalOnBean(DiscoveryClient.class)
        @ConditionalOnMissingBean
        @Conditional(MyZoneConfigurationCondition.class)
        public ServiceInstanceListSupplier zonePreferenceServiceInstanceListSupplier(
                ConfigurableApplicationContext context,
                ZonePreferenceFilter<ServiceInstance> zonePreferenceFilter) {
            return ServiceInstanceListSupplier.builder().withBlockingDiscoveryClient().with(
                    (ctx, delegate) -> new ZonePreferenceServiceInstanceListSupplier(delegate, zonePreferenceFilter)
            ).build(context);
        }
    }

    @Bean
    public ZonePreferenceFilter<ServiceInstance> zonePreferenceFilter(ZoneContext zoneContext) {
        zoneContext.setPreferenceEnabled(true);
        zoneContext.setEnabled(true);
        zoneContext.setPreferenceUpstreamSameZoneMinAvailable(1);
        return new ZonePreferenceFilter<ServiceInstance>(zoneContext, new CloudServerZoneResolver());
    }


    static class MyZoneConfigurationCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return LoadBalancerEnvironmentPropertyUtils.equalToForClientOrDefault(context.getEnvironment(),
                    "configurations", "my-zone");
        }

    }
}
