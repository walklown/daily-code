课程作业
=======

源码分析作业
----------

18-04-08
----------
1.2.0加入plugin功能


18-04-01
----------
1.手写1.0
>http://git.gupaoedu.com/zzp/workspace/blob/master/zzp-mybatis/src/main/java/com/gupaoedu/mybatis/zzpMybatis
2.手写2.0


18-03-31
----------


1. TestMapper 作者为什么要设计这样的形式来做？
为什么不是一个class而是一个interface?

>为了减少重复代码量，用接口不需要写方法体，只需要通过xml或者annotion方式设计sql


2.org.apache.ibatis.executor.BaseExecutor#queryFromDatabase 322行这行代码的意义

>增加占位符(未完成)


3.MyBatis的plugin实现机制
>对Executor、ParameterHandler、ResultSetHandler和StatementHandler的实现类通过Plugin代理来生成目标类，并且关联生成的目标类与实现的Interceptor类，这样在调用Executor、ParameterHandler、ResultSetHandler和StatementHandler的实现类方法时会调用Plugin类中的invoke方法，在调用invoke方法时会进行判断这个实现类是否需要我们实现的MyInterceptor来处理，如果是会调用MyInterceptor的interceptor方法，进行一些处理之后调用Invocation的proceed方法，其实际处理方法也是调用method.invoke方法，如果不需要MyInterceptor处理时则直接调用method.invoke方法

4.lazy loading 是怎么做到的？


18-03-28
----------

1. org.apache.ibatis.binding.MapperProxy#invoke 这个类的53行什么时候执行？

>org.apache.ibatis.binding.MapperProxy#invoke 这个类的53行判断当执行方法作用域为public，非静态或抽象时，切所属类为接口类时执行。


18-03-25
----------

1.怎么验证一级缓存的存在？

2.验证N+1问题


18-03-24
----------

1.Mapper在spring管理下其实是单例，为什么可以是一个单例？ SCOPE -> application


2.MyBatis在Spring集成下没有mapper的xml文件会不会报错，为什么？


3.手写TypeHandler

>http://git.gupaoedu.com/zzp/workspace/blob/master/zzp-mybatis/src/main/java/com/gupaoedu/mybatis/typeHandlers/TestTypeHandle.java


4.手写Plugin,多个interceptor到底谁先执行？顺序由谁决定的？



