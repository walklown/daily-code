package com.zzp.learn.walklown.jarkata.interviewing.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzp.learn.walklown.jarkata.ioc.User;

import java.io.IOException;

public class JacksonDemo {

    public static void main(String[] args) throws JsonProcessingException {
        A a = new A();
        a.setName("sss");
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(a);
        System.out.println(value);

        // 多余或缺少属性
        User user = new User();
        user.setName("a");
        user.setAge("1");
        user.setAName("zz");
        String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        System.out.println(jsonStr);
        try {
            UserLess userLess = objectMapper.readerFor(UserLess.class).readValue(jsonStr);
        } catch (IOException e) {
            System.out.println("写userLess错误");
        }
        try {
            UserMore userMore = objectMapper.readerFor(UserMore.class).readValue(jsonStr);
        } catch (IOException e) {
            System.out.println("写userMore错误");
        }
        try {
            objectMapper.getDeserializationConfig().without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            UserMore userMore = objectMapper.readerFor(UserMore.class).readValue(jsonStr);
        } catch (IOException e) {
            System.out.println("写userMore错误");
        }

        // 首字母小写，第二个字母大写
        try {
            User littleFirst = objectMapper.readerFor(User.class).readValue(jsonStr);
            System.out.println(littleFirst);
        } catch (IOException e) {
            System.out.println("写userMore错误");
        }
    }
}
