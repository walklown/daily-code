package springboot;

import org.springframework.core.ResolvableType;

import java.lang.reflect.Field;

public class Demo<T> {
    public static void main(String[] args) {
        getGeniric();
    }

    private T a;

    public static void getGeniric() {
        for (Field field : Demo.class.getFields()) {
            ResolvableType[] type = ResolvableType.forField(field, Demo.class).getGenerics();
            for (ResolvableType resolvableType : type) {
                System.out.println(resolvableType.getType().getTypeName());
            }
        }
    }

}
