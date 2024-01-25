package com.walklown.attempt.client.zone;

import com.walklown.attempt.server.zone.IMyHttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.client.support.RestTemplateAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
@Slf4j
public class ConsumerService {

    @Autowired
    private RestTemplate restTemplate;

    public String call(String name) {
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("https://my-server/"));
        RestTemplateAdapter adapter = RestTemplateAdapter.create(restTemplate);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        IMyHttpService service = factory.createClient(IMyHttpService.class);
        String result = service.hello();
        log.info("result:{}", result);
        return result;
    }
}
