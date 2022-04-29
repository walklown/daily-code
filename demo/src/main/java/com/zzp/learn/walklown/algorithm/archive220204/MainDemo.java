package com.zzp.learn.walklown.algorithm.archive220204;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class MainDemo {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now" + now);
        Instant instant = Instant.now();
        System.out.println("instant" + LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(8)));
    }
}
