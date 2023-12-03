package springboot;


import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.PriorityOrdered;

/**
 * Spring拦截器
 *
 * @author Walklown
 * @date 2023-03-16
 */
@Slf4j
public class InterceptorToAspect implements MethodInterceptor, PriorityOrdered {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterceptorToAspect.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object returnValue = methodInvocation.proceed();
        log.info("AspectByInterceptor");
        return returnValue;
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
