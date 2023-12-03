package springboot;

import com.zzp.learn.springboot.ddd.sensitive.AspectPointAnnotationAdvisor;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 切点处理初始化类
 *
 * @author Walklown
 * @date 2021-08-13
 */
@Component
public class InterceptorPostProcessor extends AbstractBeanFactoryAwareAdvisingPostProcessor {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);

        AspectPointAnnotationAdvisor advisor = new AspectPointAnnotationAdvisor();
        advisor.setBeanFactory(beanFactory);
        this.advisor = advisor;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
