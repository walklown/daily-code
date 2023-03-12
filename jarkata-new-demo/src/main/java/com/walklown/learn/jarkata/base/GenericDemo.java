package com.walklown.learn.jarkata.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzp.learn.walklown.jarkata.interviewing.jackson.JacksonDemo;

import java.lang.reflect.ParameterizedType;

public class GenericDemo {

    //    public static void main(String[] args) {
//        A<String> a = new A<>();
//        System.out.println(a.getClass().getGenericSuperclass());
//        System.out.println(a.getClass().getGenericSuperclass().getTypeName());
//        System.out.println(((ParameterizedType)(a.getClass().getGenericSuperclass())).getActualTypeArguments());
//    }
//
//    static class A<T> {
//        T body;
//    }
//
    static class B {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        B b = new B();
        b.name = "test";
        Object a = b;
        System.out.println(a);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(a));
        System.out.println(a.getClass());
    }
}
