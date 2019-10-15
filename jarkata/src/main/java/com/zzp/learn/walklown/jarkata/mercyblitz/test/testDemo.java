package com.zzp.learn.walklown.jarkata.mercyblitz.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzp.learn.walklown.jarkata.ioc.User;

import java.io.IOException;

public class testDemo {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setName("a");
        user.setAge("1");
        String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        System.out.println(jsonStr);
        try {
            UserLess userLess = mapper.readerFor(UserLess.class).readValue(jsonStr);
        } catch (IOException e) {
            System.out.println("写userLess错误");
        }
        try {
            UserMore userMore = mapper.readerFor(UserMore.class).readValue(jsonStr);
        } catch (IOException e) {
            System.out.println("写userMore错误");
        }
        try {
            mapper.getDeserializationConfig().without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            UserMore userMore = mapper.readerFor(UserMore.class).readValue(jsonStr);
        } catch (IOException e) {
            System.out.println("写userMore错误");
        }
    }
}

