package com.gupaoedu.spring.demo.mvc.action;

import com.gupaoedu.spring.demo.service.IDemoService;
import com.gupaoedu.spring.annotation.Autowried;
import com.gupaoedu.spring.annotation.Controller;
import com.gupaoedu.spring.annotation.RequestMapping;

@Controller
public class MyAction {

    @Autowried
    IDemoService demoService;

    @RequestMapping("/index.html")
    public String query() {
        System.out.println("index");
        return "index";
    }

}
