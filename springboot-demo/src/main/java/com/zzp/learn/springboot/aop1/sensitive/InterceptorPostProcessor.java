package com.zzp.learn.springboot.aop1.sensitive;

import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * 切点处理初始化类
 *
 * @author 守愚（张智沛）
 * @date 2021-08-13
 */
@Component
public class InterceptorPostProcessor extends AbstractBeanFactoryAwareAdvisingPostProcessor implements PriorityOrdered {

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
