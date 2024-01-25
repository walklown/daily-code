package com.walklown.attempt.server.zone;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import io.microsphere.multiple.active.zone.ZoneAttachmentHandler;
import io.microsphere.multiple.active.zone.ZoneContext;
import io.microsphere.multiple.active.zone.eureka.ZoneAttachmentPreRegistrationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.cloud.configuration.SSLContextFactory;
import org.springframework.cloud.configuration.TlsProperties;
import org.springframework.cloud.netflix.eureka.http.EurekaClientHttpRequestFactorySupplier;
import org.springframework.cloud.netflix.eureka.http.RestTemplateDiscoveryClientOptionalArgs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
public class RegistraterConfiguration {

    @Autowired
    private ApplicationInfoManager applicationInfoManager;

    @Autowired
    private ZoneContext zoneContext;

    @Bean
    @ConditionalOnClass(name = "org.springframework.web.client.RestTemplate")
    @ConditionalOnMissingClass("org.glassfish.jersey.client.JerseyClient")
    @ConditionalOnMissingBean(value = { AbstractDiscoveryClientOptionalArgs.class }, search = SearchStrategy.CURRENT)
    @ConditionalOnProperty(prefix = "eureka.client", name = "webclient.enabled", matchIfMissing = true,
            havingValue = "false")
    @Primary
    public RestTemplateDiscoveryClientOptionalArgs restTemplateDiscoveryClientOptionalArgs(TlsProperties tlsProperties,
                                                                                           EurekaClientHttpRequestFactorySupplier eurekaClientHttpRequestFactorySupplier)
            throws GeneralSecurityException, IOException {
        RestTemplateDiscoveryClientOptionalArgs result = new RestTemplateDiscoveryClientOptionalArgs(
                eurekaClientHttpRequestFactorySupplier);
        result.setPreRegistrationHandler(new ZoneAttachmentPreRegistrationHandler(applicationInfoManager,
                new ZoneAttachmentHandler(zoneContext)));
        setupTLS(result, tlsProperties);
        return result;
    }

    private static void setupTLS(AbstractDiscoveryClientOptionalArgs<?> args, TlsProperties properties)
            throws GeneralSecurityException, IOException {
        if (properties.isEnabled()) {
            SSLContextFactory factory = new SSLContextFactory(properties);
            args.setSSLContext(factory.createSSLContext());
        }
    }
}
