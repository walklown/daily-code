#### Java 8 新特性
https://www.oracle.com/java/technologies/javase/8-whats-new.html

* Java编程语法
  * Lambda表达式（Lambda Expressions）
  * 方法引用（Method references）
    * Class::new
    * Class::static_method
    * Class::method
    * instance::method
  * 接口类支持【default】方法：默认方法使您能够向库的接口添加新功能，并确保与为这些接口的旧版本编写的代码的二进制兼容性。
    * 如【java.util.Collection】中新增了【Spliterator<E> spliterator()】、【Stream<E> stream()】、【Stream<E> parallelStream()】三个默认方法以支持流API，其子类无需变更即可使用


#### Java 9 新特性
#### Java 10 新特性
#### Java 11 新特性

`官网公开的 17 个 JEP（JDK Enhancement Proposal 特性增强提议）`

* 181: [Nest-Based Access Control](http://openjdk.java.net/jeps/181)（基于嵌套的访问控制）
* 309: [Dynamic Class-File Constants](http://openjdk.java.net/jeps/309)（动态的类文件常量）
* 315: [Improve Aarch64 Intrinsics](http://openjdk.java.net/jeps/315)（改进 Aarch64 Intrinsics）
* 318: [[[Epsilon: A No-Op Garbage Collector](http://openjdk.java.net/jeps/318)（Epsilon 垃圾回收器，又被称为"No-Op（无操作）"回收器）
* 320: [Remove the Java EE and CORBA Modules](http://openjdk.java.net/jeps/320)（移除 Java EE 和 CORBA 模块，JavaFX 也已被移除）
* 321: [HTTP Client (Standard)](http://openjdk.java.net/jeps/321)
* 323: [Local-Variable Syntax for Lambda Parameters](http://openjdk.java.net/jeps/323)（用于 Lambda 参数的局部变量语法）
* 324: [Key Agreement with Curve25519 and Curve448](http://openjdk.java.net/jeps/324)（采用 Curve25519 和 Curve448 算法实现的密钥协议）
* 327: [Unicode 10](http://openjdk.java.net/jeps/327)
* 328: [Flight Recorder](http://openjdk.java.net/jeps/328)（飞行记录仪）
* 329: [ChaCha20 and Poly1305 Cryptographic Algorithms](http://openjdk.java.net/jeps/329)（实现 ChaCha20 和 Poly1305 加密算法）
* 330: [Launch Single-File Source-Code Programs](http://openjdk.java.net/jeps/330)（启动单个 Java 源代码文件的程序）
* 331: [Low-Overhead Heap Profiling](http://openjdk.java.net/jeps/331)（低开销的堆分配采样方法）
* 332: [Transport Layer Security (TLS) 1.3](http://openjdk.java.net/jeps/332)（对 TLS 1.3 的支持）
* 333: [ZGC: A Scalable Low-Latency Garbage Collector](http://openjdk.java.net/jeps/333) (Experimental)（ZGC：可伸缩的低延迟垃圾回收器，处于实验性阶段）
* 335: [Deprecate the Nashorn JavaScript Engine](http://openjdk.java.net/jeps/335)（弃用 Nashorn JavaScript 引擎）
* 336: [Deprecate the Pack200 Tools and API](http://openjdk.java.net/jeps/336)（弃用 Pack200 工具及其 API）


#### Java 12 新特性

#### Java 13 新特性

#### Java 15 新特性

https://openjdk.org/projects/jdk/15/