/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zzp.learn.springboot.aop1.sensitive;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 切点处理类
 *
 * @author 守愚（张智沛）
 * @date 2021-08-14
 */
public class SensitiveAnnotationAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {

	/**
	 * 处理执行类
	 */
	private final Advice advice;

	/**
	 * 切点类
	 */
	private final Pointcut pointcut;


	/**
	 * Create a new {@code AsyncAnnotationAdvisor} for bean-style configuration.
	 */
	public SensitiveAnnotationAdvisor() {
		Set<Class<? extends Annotation>> asyncAnnotationTypes = new LinkedHashSet<>(2);
		asyncAnnotationTypes.add(Sensitive.class);
		this.advice = new SensitiveAspect();
		this.pointcut = buildPointcut(asyncAnnotationTypes);
	}

	/**
	 * Set the {@code BeanFactory} to be used when looking up executors by qualifier.
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		if (this.advice instanceof BeanFactoryAware) {
			((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
		}
	}


	@Override
	public Advice getAdvice() {
		return this.advice;
	}

	@Override
	public Pointcut getPointcut() {
		return this.pointcut;
	}

	/**
	 * Calculate a pointcut for the given async annotation types, if any.
	 * @param asyncAnnotationTypes the async annotation types to introspect
	 * @return the applicable Pointcut object, or {@code null} if none
	 */
	protected Pointcut buildPointcut(Set<Class<? extends Annotation>> asyncAnnotationTypes) {
		ComposablePointcut result = null;
		for (Class<? extends Annotation> asyncAnnotationType : asyncAnnotationTypes) {
			Pointcut cpc = new AnnotationMatchingPointcut(asyncAnnotationType, true);
			Pointcut mpc = new AnnotationMatchingPointcut(null, asyncAnnotationType, true);
			if (result == null) {
				result = new ComposablePointcut(cpc);
			}
			else {
				result.union(cpc);
			}
			result = result.union(mpc);
		}
		return (result != null ? result : Pointcut.TRUE);
	}

}
