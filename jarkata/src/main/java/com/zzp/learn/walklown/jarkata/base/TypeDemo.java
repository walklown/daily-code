package com.zzp.learn.walklown.jarkata.base;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class TypeDemo {

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
//        System.out.println(TestType.class.getField("name"));
//        System.out.println(TestType.class.getField("tArray"));
//        System.out.println(TestType.class.getField("tList"));
//        System.out.println(TestType.class.getField("numList"));
//        System.out.println(TestType.class.getField("a"));
//        System.out.println(TestType.class.getField("argsArray"));
        new TypeDemo().showType();
    }

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

    private void showType() throws NoSuchMethodException {
        StringBuilder typeNameStrBuilder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            typeNameStrBuilder.append(" ");
        }
        // 注意 int.class 和 Integer.class 是不一样的(没有所谓的自动装箱、自动拆箱机制)，不能互用
        Class<?> clazz = List.class;
        Method method = TypeDemo.class.getMethod("testType", int.class, Boolean.class, clazz, clazz, clazz, clazz, clazz, Map.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes(); //按照方法参数声明顺序返回参数的 Type 数组
        for (Type type : genericParameterTypes) {
            System.out.println("  " + getTypeInfo("|", type));
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] types = parameterizedType.getActualTypeArguments(); //返回表示此类型【实际类型参数】的 Type 数组
                for (int i = 0; i < types.length; i++) {
                    System.out.println(getTypeInfo("| -> " + i + " : ", types[i]));
                }
            }
        }
    }

    private String getTypeInfo(String pre, Type type) {
        String typeName = type.getTypeName();
        Class<?> clazz = type.getClass();
        Class<?>[] interfaces = clazz.getInterfaces();
        StringJoiner typeInterface = new StringJoiner(", ");
        for (Class<?> clazzType : interfaces) {
            typeInterface.add(clazzType.getTypeName());
        }
        return pre + typeName + "    |" + clazz.getSimpleName() + "    |" + typeInterface + "|";
    }

    public <T> void testType(int i, Boolean b, List<String> a1, List<ArrayList<String>> a2, List<T> a3, //
                             List<? extends Number> a4, List<ArrayList<String>[]> a5, Map<Boolean, Integer> a6) {
    }
}
