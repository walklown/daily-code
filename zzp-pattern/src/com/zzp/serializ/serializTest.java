package com.zzp.serializ;

public class serializTest {
    public static void main(String[] args) {
        User user = new User();
        user.setAge(18);
        user.setName("zzp");
        user.setHobby("my");
        Serialization serialization = new Serialization();
        byte[] se = serialization.serializer(user);
        System.out.println(se+" length:"+se.length+"\n");
        System.out.println(serialization.deserializer(se, User.class)+"\n");

    }
}
