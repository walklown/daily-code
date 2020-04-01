package com.gupaoedu.spring.mvc.servlet;

import com.gupaoedu.spring.annotation.*;
import com.gupaoedu.spring.aop.GPAopProxyUtils;
import com.gupaoedu.spring.context.MyApplicationContext;
import com.gupaoedu.spring.mvc.GPHandlerAdapter;
import com.gupaoedu.spring.mvc.GPHandlerMapping;
import com.gupaoedu.spring.mvc.GPViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * Created by Tom on 2018/4/18.
 */
public class DispatchServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    private Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();

    private List<String> classNames = new ArrayList<String>();

    private List<GPViewResolver> viewResolvers = new ArrayList<GPViewResolver>();

    private List<GPHandlerMapping> handlerMappings = new ArrayList<GPHandlerMapping>();

    private Map<GPHandlerMapping, GPHandlerAdapter> handlerAdapters = new HashMap<GPHandlerMapping, GPHandlerAdapter>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("---------- 调用doPost ----------");
        try {
//            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("<font size='25' color='blue'>500 Exception</font><br/>Details:<br/>" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", "")
                    .replaceAll("\\s", "\r\n") + "<font color='green'><i>Copyright@GupaoEDU</i></font>");
            e.printStackTrace();
        }

    }

    private final String LOCATION = "contextConfigLocation";

    @Override
    public void init(ServletConfig config) throws ServletException {

        //开始初始化的进程

        //定位
        MyApplicationContext applicationContext = new MyApplicationContext(config.getInitParameter(LOCATION));
//        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //加载
//        doScanner(contextConfig.getProperty("scanPackage"));

        //注册
//        doRegistry();

        //自动依赖注入

        //在Spring中是通过调用getBean方法才出发依赖注入的
//        doAutowired();


//        DemoAction action = (DemoAction)beanMap.get("demoAction");
//        action.query(null,null,"Tom");

        //如果是SpringMVC会多设计一个HnandlerMapping

        //将@RequestMapping中配置的url和一个Method关联上
        //以便于从浏览器获得用户输入的url以后，能够找到具体执行的Method通过反射去调用
//        initHandlerMapping();

    }

    protected void initStrategies(MyApplicationContext context) {

        //有九种策略
        // 针对于每个用户请求，都会经过一些处理的策略之后，最终才能有结果输出
        // 每种策略可以自定义干预，但是最终的结果都是一致
        // ModelAndView

        // =============  这里说的就是传说中的九大组件 ================
        initMultipartResolver(context);//文件上传解析，如果请求类型是multipart将通过MultipartResolver进行文件上传解析
        initLocaleResolver(context);//本地化解析
        initThemeResolver(context);//主题解析

        /** 我们自己会实现 */
        //GPHandlerMapping 用来保存Controller中配置的RequestMapping和Method的一个对应关系
        initHandlerMappings(context);//通过HandlerMapping，将请求映射到处理器
        /** 我们自己会实现 */
        //HandlerAdapters 用来动态匹配Method参数，包括类转换，动态赋值
        initHandlerAdapters(context);//通过HandlerAdapter进行多类型的参数动态匹配

        initHandlerExceptionResolvers(context);//如果执行过程中遇到异常，将交给HandlerExceptionResolver来解析
        initRequestToViewNameTranslator(context);//直接解析请求到视图名

        /** 我们自己会实现 */
        //通过ViewResolvers实现动态模板的解析
        //自己解析一套模板语言
        initViewResolvers(context);//通过viewResolver解析逻辑视图到具体视图实现

        initFlashMapManager(context);//flash映射管理器
    }

    private void initFlashMapManager(MyApplicationContext context) {
    }

    private void initRequestToViewNameTranslator(MyApplicationContext context) {
    }

    private void initHandlerExceptionResolvers(MyApplicationContext context) {
    }

    private void initThemeResolver(MyApplicationContext context) {
    }

    private void initLocaleResolver(MyApplicationContext context) {
    }

    private void initMultipartResolver(MyApplicationContext context) {
    }


    //将Controller中配置的RequestMapping和Method进行一一对应
    private void initHandlerMappings(MyApplicationContext context) {
        //按照我们通常的理解应该是一个Map
        //Map<String,Method> map;
        //map.put(url,Method)

        //首先从容器中取到所有的实例
        String[] beanNames = context.getBeanDefinitionNames();
        try {
            for (String beanName : beanNames) {
                //到了MVC层，对外提供的方法只有一个getBean方法
                //返回的对象不是BeanWrapper，怎么办？
                Object proxy = context.getBean(beanName);
                Object controller = GPAopProxyUtils.getTargetObject(proxy);
                Class<?> clazz = controller.getClass();
                //但是不是所有的牛奶都叫特仑苏
                if (!clazz.isAnnotationPresent(Controller.class)) {
                    continue;
                }

                String baseUrl = "";

                if (clazz.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                    baseUrl = requestMapping.value();
                }

                //扫描所有的public方法
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (!method.isAnnotationPresent(RequestMapping.class)) {
                        continue;
                    }

                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                    String regex = ("/" + baseUrl + requestMapping.value().replaceAll("\\*", ".*")).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);
                    this.handlerMappings.add(new GPHandlerMapping(pattern, controller, method));
                    System.out.println("Mapping: " + regex + " , " + method);

                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void initHandlerAdapters(MyApplicationContext context) {
        //在初始化阶段，我们能做的就是，将这些参数的名字或者类型按一定的顺序保存下来
        //因为后面用反射调用的时候，传的形参是一个数组
        //可以通过记录这些参数的位置index,挨个从数组中填值，这样的话，就和参数的顺序无关了

        for (GPHandlerMapping handlerMapping : this.handlerMappings) {

            //每一个方法有一个参数列表，那么这里保存的是形参列表
            Map<String, Integer> paramMapping = new HashMap<String, Integer>();


            //这里只是出来了命名参数
            Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation a : pa[i]) {
                    if (a instanceof RequestParam) {
                        String paramName = ((RequestParam) a).value();
                        if (!"".equals(paramName.trim())) {
                            paramMapping.put(paramName, i);
                        }
                    }
                }
            }

            //接下来，我们处理非命名参数
            //只处理Request和Response
            Class<?>[] paramTypes = handlerMapping.getMethod().getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                Class<?> type = paramTypes[i];
                if (type == HttpServletRequest.class ||
                        type == HttpServletResponse.class) {
                    paramMapping.put(type.getName(), i);
                }
            }


            this.handlerAdapters.put(handlerMapping, new GPHandlerAdapter(paramMapping));
        }

    }

    private void initViewResolvers(MyApplicationContext context) {
        //在页面敲一个 http://localhost/first.html
        //解决页面名字和模板文件关联的问题
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);

        for (File template : templateRootDir.listFiles()) {
            this.viewResolvers.add(new GPViewResolver(template.getName(), template));
        }

    }

    private void doRegistry() {

        if (classNames.isEmpty()) {
            return;
        }

        try {

            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);


                //在Spring中用的多个子方法来处理的
                if (clazz.isAnnotationPresent(Controller.class)) {

                    String beanName = lowerFirstCase(clazz.getSimpleName());

                    //在Spring中在这个阶段不是不会直接put instance，这里put的是BeanDefinition
                    beanMap.put(beanName, clazz.newInstance());

                } else if (clazz.isAnnotationPresent(Service.class)) {

                    Service service = clazz.getAnnotation(Service.class);

                    //默认用类名首字母注入
                    //如果自己定义了beanName，那么优先使用自己定义的beanName
                    //如果是一个接口，使用接口的类型去自动注入

                    //在Spring中同样会分别调用不同的方法 autowriedByName autowritedByType

                    String beanName = service.value();
                    if ("".equals(beanName.trim())) {
                        beanName = lowerFirstCase(clazz.getSimpleName());
                    }

                    Object instance = clazz.newInstance();

                    beanMap.put(beanName, instance);

                    Class<?>[] interfaces = clazz.getInterfaces();

                    for (Class<?> i : interfaces) {
                        beanMap.put(i.getName(), instance);
                    }

                } else {
                    continue;
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doAutowired() {
        if (beanMap.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {

            Field[] fields = entry.getValue().getClass().getDeclaredFields();

            for (Field field : fields) {

                if (!field.isAnnotationPresent(Autowried.class)) {
                    continue;
                }

                Autowried autowried = field.getAnnotation(Autowried.class);

                String beanName = autowried.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }
                field.setAccessible(true);

                try {
                    field.set(entry.getValue(), beanMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    private void doScanner(String packageName) {

        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));

        File classDir = new File(url.getFile());

        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else {
                classNames.add(packageName + "." + file.getName().replace(".class", ""));
            }
        }


    }

    private void doLoadConfig(String location) {
        //在Spring中是通过Reader去查找和定位对不对
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:", ""));

        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }


    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
