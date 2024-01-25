package com.walklown.attempt.client.zone;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/{name}")
    public String home(String name) {
        String result = consumerService.call(name);
        log.info("result:{}", result);
        return result;
    }
}
