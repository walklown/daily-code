package main.java;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;

public class InvokeGroovy {
    public static void main(String[] args) {
        ClassLoader cl = new InvokeGroovy().getClass().getClassLoader();
        GroovyClassLoader groovyCl = new GroovyClassLoader(cl);
        try {
            @SuppressWarnings("rawtypes")
            Class groovyClass = groovyCl.parseClass(new File(InvokeGroovy.class.getResource("/groovy/Foo.groovy").toURI()));
            IFoo foo = (IFoo) groovyClass.getConstructor().newInstance();
            System.out.println(foo.run(2, "More parameter..."));

            System.out.println("=============================");

            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            System.out.println(groovyObject.invokeMethod("run", new Object[]{new Integer(2), "More parameter..."}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
