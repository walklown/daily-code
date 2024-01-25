package com.walklown.attempt.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableScheduling
public class LogPrinter {

    @Scheduled(fixedRate = 100)
    public void print() {
        log.info("hehehe" + System.currentTimeMillis());
    }
}
