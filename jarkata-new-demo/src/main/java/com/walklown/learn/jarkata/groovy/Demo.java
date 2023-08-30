package com.walklown.learn.jarkata.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        File file = new File(Demo.class.getClassLoader().getResource("Me.groovy").getPath());
        System.out.println(file);
        GroovyClassLoader loader = new GroovyClassLoader();
        Class fileCreator = loader.parseClass(file);
        GroovyObject object = (GroovyObject) fileCreator.newInstance();
        System.out.println(object.invokeMethod("sleep", null));
    }
}
