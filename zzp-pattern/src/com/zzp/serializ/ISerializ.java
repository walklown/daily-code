package com.zzp.serializ;

public interface ISerializ {
    <T> byte[] serializer(T obj);

    <T> T deserializer(byte[] data, Class<T> clazz);
}
