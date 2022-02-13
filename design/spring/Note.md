ignoreDependencyInterface 暂时未验证有效，根据网上文章，主要在xml配置场景下有效

1、执行 SpringApplication#run
2、SpringApplication.createApplicationContext 
  -> (ConfigurableApplicationContext) AnnotationConfigServletWebServerApplicationContext
    -> 构造函数中 beanFactory = new DefaultListableBeanFactory()
3、AnnotationConfigServletWebServerApplicationContext#refresh

BeanFactoryPostProcessor & BeanPostProcessor