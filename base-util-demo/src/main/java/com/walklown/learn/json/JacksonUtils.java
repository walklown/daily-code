//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.walklown.learn.json;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JacksonUtils {
    public static final ObjectMapper OBJ_MAPPER = new ObjectMapper();

    public JacksonUtils() {
    }

    public static String toJSONString(Object object) {
        try {
            return OBJ_MAPPER.writeValueAsString(object);
        } catch (IOException var2) {
            throw new JacksonException(var2);
        }
    }

    public static Map<String, Object> toMap(Object object) {
        JavaType type = OBJ_MAPPER.getTypeFactory().constructParametricType(Map.class, new Class[]{String.class, Object.class});

        try {
            return (Map)OBJ_MAPPER.convertValue(object, type);
        } catch (IllegalArgumentException var3) {
            throw new JacksonException(var3);
        }
    }

    public static JSONObject toJSON(Object object) {
        try {
            return (JSONObject)OBJ_MAPPER.convertValue(object, JSONObject.class);
        } catch (IllegalArgumentException var2) {
            throw new JacksonException(var2);
        }
    }

    public static <T> T parseObject(String str, Class<T> T) {
        try {
            return OBJ_MAPPER.readValue(str, T);
        } catch (IOException var3) {
            throw new JacksonException(var3);
        }
    }

    public static JSONObject parseObject(String str) {
        return (JSONObject)parseObject(str, JSONObject.class);
    }

    public static <T> T toJavaObject(JSONObject jsonObject, Class<T> clazz) {
        try {
            return OBJ_MAPPER.convertValue(jsonObject, clazz);
        } catch (IllegalArgumentException var3) {
            throw new JacksonException(var3);
        }
    }

    public static <E> List<E> parseArray(String str, Class<E> elementClass) {
        JavaType type = OBJ_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, new Class[]{elementClass});

        try {
            return (List)OBJ_MAPPER.readValue(str, type);
        } catch (IOException var4) {
            throw new JacksonException(var4);
        }
    }

    public static JSONArray parseArray(String str) {
        return (JSONArray)parseObject(str, JSONArray.class);
    }

    public static <C extends Collection<E>, E> C parseArray(String str, Class<C> collectionClass, Class<E> elementClass) {
        JavaType type = OBJ_MAPPER.getTypeFactory().constructParametricType(collectionClass, new Class[]{elementClass});

        try {
            return OBJ_MAPPER.readValue(str, type);
        } catch (IOException var5) {
            throw new JacksonException(var5);
        }
    }

    public static Map<String, Object> parseMap(String str) {
        return parseMap(str, String.class, Object.class);
    }

    public static <K, V> Map<K, V> parseMap(String str, Class<K> keyClass, Class<V> valueClass) {
        JavaType type = OBJ_MAPPER.getTypeFactory().constructParametricType(Map.class, new Class[]{keyClass, valueClass});

        try {
            return (Map)OBJ_MAPPER.readValue(str, type);
        } catch (IOException var5) {
            throw new JacksonException(var5);
        }
    }

    static {
        OBJ_MAPPER.registerModule(new JsonOrgModule());
        OBJ_MAPPER.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        OBJ_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        OBJ_MAPPER.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
        OBJ_MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        OBJ_MAPPER.enable(com.fasterxml.jackson.core.JsonGenerator.Feature.IGNORE_UNKNOWN);
    }
}
