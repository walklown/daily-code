package springboot;

import com.zzp.learn.springboot.imports.annotation.EnableHelloWorld;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@EnableHelloWorld
@Configuration
public class EnableHelloWorldBootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.register(EnableHelloWorldBootstrap.class);
        context.refresh();
        String result = context.getBean("helloWorld", String.class);
        System.out.printf("helloWorld = %s \n", result);
        context.close();
    }
}
