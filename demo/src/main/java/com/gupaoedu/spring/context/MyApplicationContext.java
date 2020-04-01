package com.gupaoedu.spring.context;

import com.gupaoedu.spring.beans.BeanDefinition;
import com.gupaoedu.spring.beans.BeanPostProcessor;
import com.gupaoedu.spring.beans.BeanWrapper;
import com.gupaoedu.spring.core.BeanFactory;
import com.gupaoedu.spring.core.suport.BeanDefinitionReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class MyApplicationContext implements BeanFactory {
    private String[] configLocations;
    private BeanDefinitionReader reader;
    //配置信息
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    //注册式单例
    private Map<String, Object> beanCacheMap = new ConcurrentHashMap<String, Object>();
    //被代理过的bean
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<String, BeanWrapper>();

    public MyApplicationContext(String... location) {
        this.configLocations = location;
        refresh();
    }

    public void refresh() {

        //定位
        this.reader = new BeanDefinitionReader(configLocations);

        List<String> beanDefinitions = reader.loadBeanDefinations();
        //注册
        doRegisty(beanDefinitions);

        //加载
        doAutorited();
    }

    private void doAutorited() {
        for (Map.Entry<String, BeanDefinition> entry : this.beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            if (!entry.getValue().isLazyInit()) {
                getBean(beanName);
            }
        }
    }

    private void doRegisty(List<String> beanDefinitions) {
        try {
            for (String className : beanDefinitions) {
                Class<?> beanClass = Class.forName(className);
                if (beanClass.isInterface()) {
                    continue;
                }
                BeanDefinition beanDefinition = reader.registerBean(className);
                if (beanDefinition != null) {
                    this.beanDefinitionMap.put(beanDefinition.getFactoryName(), beanDefinition);
                }
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    this.beanDefinitionMap.put(i.getName(), beanDefinition);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        String className = beanDefinition.getBeanClassName();
        try {
            //生成通知事件
            BeanPostProcessor beanPostProcessor = new BeanPostProcessor();

            Object instance = instantionBean(beanDefinition);

            beanPostProcessor.postProcessBeforeInitialization(instance, beanName);

            if (null == instance) {
                return null;
            }
            BeanWrapper beanWrapper = new BeanWrapper(instance, instance);
            this.beanWrapperMap.put(beanName, beanWrapper);

            beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            return beanWrapperMap.get(beanName).getWrapperInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object instantionBean(BeanDefinition beanDefinition) {
        Object instance = null;
        String className = beanDefinition.getBeanClassName();
        try {
            if (this.beanCacheMap.containsKey(className)) {
                instance = this.beanCacheMap.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.beanCacheMap.put(className, instance);
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Properties getConfig() {
        return this.reader.getConfig();
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }
}
