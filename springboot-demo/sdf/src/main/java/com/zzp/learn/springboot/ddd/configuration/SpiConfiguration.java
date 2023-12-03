package springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpiConfiguration {

    @Bean
    public ObjectMapper jacksonUtils() {
        return new ObjectMapper();
    }
}
