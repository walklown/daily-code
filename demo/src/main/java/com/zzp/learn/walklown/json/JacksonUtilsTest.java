package com.zzp.learn.walklown.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Jackson处理泛型
 * {@link com.zzp.learn.walklown.jarkata.base.GenericParameterTypeDemo}
 *
 * @author 守愚（张智沛）
 * @date 2023-01-30
 */
public class JacksonUtilsTest {

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, JsonProcessingException {
//        Object o = JacksonUtils.testDecode("{\"id\":\"123\", \"name\":\"a\"}", Node.class);
//        System.out.println(o);
//        Object o1 = JacksonUtils.testDecode("{\"id\":\"123\", \"name\":\"a\", \"child\":{\"id\":\"4\", \"name\":\"b\"}}", Node.class.getGenericSuperclass());
//        System.out.println(o1);
        // 注意 int.class 和 Integer.class 是不一样的(没有所谓的自动装箱、自动拆箱机制)，不能互用
        Class<?> clazz = List.class;
        Method method = JacksonUtilsTest.class.getMethod("testType", clazz, clazz, clazz, clazz, clazz, Map.class);
        //按照方法参数声明顺序返回参数的 Type 数组
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        List<String> aList = new ArrayList<>();
        aList.add("a");
        aList.add("b");
        String json = JacksonUtils.toJSONString(aList);
        System.out.println(JacksonUtils.OBJ_MAPPER.readValue(json, new TypeReference<List<String>>() {
        }).toString());
        // List<String> a1
        JavaType javaType = JacksonUtils.OBJ_MAPPER.getTypeFactory().constructType(genericParameterTypes[0]);
        System.out.println(JacksonUtils.OBJ_MAPPER.readValue(json, javaType).toString());
        // List<ArrayList<String>> a2
        JavaType javaType1 = JacksonUtils.OBJ_MAPPER.getTypeFactory().constructType(genericParameterTypes[1]);
        System.out.println(JacksonUtils.OBJ_MAPPER.readValue("[[\"a\",\"b\"], [\"a\",\"b\"]]", javaType1).toString());
        // List<T> a3
        JavaType javaType2 = JacksonUtils.OBJ_MAPPER.getTypeFactory().constructType(genericParameterTypes[2]);
        System.out.println(JacksonUtils.OBJ_MAPPER.readValue("[\"a\",\"b\"]", javaType2).toString());
        // List<? extends Number> a4
        JavaType javaType3 = JacksonUtils.OBJ_MAPPER.getTypeFactory().constructType(genericParameterTypes[3]);
        System.out.println(JacksonUtils.OBJ_MAPPER.readValue("[1,2]", javaType3).toString());
        // List<ArrayList<String>[]> a5
        JavaType javaType4 = JacksonUtils.OBJ_MAPPER.getTypeFactory().constructType(genericParameterTypes[4]);
        System.out.println(JacksonUtils.OBJ_MAPPER.readValue("[[[\"a\",\"b\"]], [[\"a\",\"b\"]]]", javaType4).toString());
        // Map<Boolean, Integer> a6
        JavaType javaType5 = JacksonUtils.OBJ_MAPPER.getTypeFactory().constructType(genericParameterTypes[5]);
        System.out.println(JacksonUtils.OBJ_MAPPER.readValue("{\"true\":1, \"false\":2}", javaType5).toString());
    }
//    public static Object testDecode(String json, Type type) {
//        if (type instanceof java.lang.reflect.ParameterizedType) {
//            // 泛型List、Map
//            Type rawType = ((ParameterizedType) type).getRawType();
//            return JacksonUtils.parseObject(json, (Class<? extends Object>) rawType);
//        } else if (type instanceof java.lang.reflect.GenericArrayType) {
//            // 带有泛型的数组，即T[]
//            Type rawType = ((GenericArrayType) type).getGenericComponentType();
//            return JacksonUtils.parseObject(json, (Class<? extends Object>) rawType);
//        } else if (type instanceof java.lang.reflect.TypeVariable) {
//            // List<T>
//            Type rawType = ((TypeVariable) type).getClass();
//            return JacksonUtils.parseObject(json, (Class<? extends Object>) rawType);
//        } else if (type instanceof java.lang.reflect.WildcardType) {
//            // List< ? extends Number>
//            Type rawType = ((WildcardType) type).getClass();
//            return JacksonUtils.parseObject(json, (Class<? extends Object>) rawType);
//        } else if (type instanceof Class) {
//            // 不仅仅包含我们平常所指的类、枚举、数组、注解，还包括基本类型int、float等等
//            return JacksonUtils.parseObject(json, (Class<? extends Object>) type);
//        } else {
//            return null;
//        }
//    }

    @Getter
    @Setter
    public static class TestType<T> {

        public List<String> name;

        public T[] tArray;

        public List<T> tList;

        public List< ? extends Number> numList;

        public int a;

        public int[] aArray;

    }

    public <T> void testType(List<String> a1, List<ArrayList<String>> a2, List<T> a3,
                             List<? extends Number> a4, List<ArrayList<String>[]> a5, Map<Boolean, Integer> a6) {
    }
}
