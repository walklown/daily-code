package com.zzp.learn.walklown.jarkata.interviewing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonDemo {

    public static void main(String[] args) throws JsonProcessingException {
        A a = new A();
        a.setName("sss");
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(a);
        System.out.println(value);
    }
}
