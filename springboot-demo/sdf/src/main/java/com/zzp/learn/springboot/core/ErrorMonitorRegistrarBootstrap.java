package springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class ErrorMonitorRegistrarBootstrap {

    // GenericApplicationListener
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ErrorMonitorRegistrarBootstrap.class);
        ErrorMonitorRegistrar asel = new ErrorMonitorRegistrar();
        springApplication.addListeners(asel);
        ConfigurableApplicationContext context = springApplication.run(args);
        log.info("{}", context.getBean(ErrorMonitorRegistrarBootstrap.class));
    }
}

