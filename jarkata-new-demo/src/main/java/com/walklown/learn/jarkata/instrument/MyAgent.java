package com.walklown.learn.jarkata.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

public class MyAgent {

    public static void permain(String args, Instrumentation instrumentation) {
        ClassFileTransformer transformer = new MyClassFileTransformer();
        instrumentation.addTransformer(transformer);
    }
}
