package com.zzp.pattern.prototype;

import java.util.ArrayList;
import java.util.List;

public class Prototype implements Cloneable {
    public String name;

    public List list;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
