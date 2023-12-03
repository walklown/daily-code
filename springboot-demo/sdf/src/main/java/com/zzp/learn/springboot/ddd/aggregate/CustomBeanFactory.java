package springboot;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CustomBeanFactory {

    @Bean(autowireCandidate = false, name = "aggregatePrototype")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public IAggregate wire() {
        return new Aggregate();
    }
}
