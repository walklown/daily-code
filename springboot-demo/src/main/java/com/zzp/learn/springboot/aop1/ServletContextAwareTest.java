//package com.zzp.learn.springboot.aop1;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.ServletContextAware;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletContext;
//
//@Component
//public class ServletContextAwareTest implements ServletContextAware {
//
//    private ServletContext servletContext;
//
//    private ISiteManagerBusinessApiService siteManagerBusinessApiService;
//
//    @Autowired
//    @Override
//    public void setServletContext(ServletContext servletContext) {
//        this.servletContext = servletContext;
//    }
//
//    public ServletContext getServletContext() {
//        return servletContext;
//    }
//
//    public ISiteManagerBusinessApiService getSiteManagerBusinessApiService() {
//        return siteManagerBusinessApiService;
//    }
//
//    public void setSiteManagerBusinessApiService(ISiteManagerBusinessApiService siteManagerBusinessApiService) {
//        this.siteManagerBusinessApiService = siteManagerBusinessApiService;
//    }
//}
