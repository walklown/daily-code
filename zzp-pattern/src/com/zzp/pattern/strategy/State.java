package com.zzp.pattern.strategy;

public class State {
    private String code;
    private String name;
    private String path;

    public State(String code, String name, String path) {
        this.code = code;
        this.name = name;
        this.path = path;
    }

    @Override
    public String toString(){
        return "状态:"+code+",文件名:"+name+",上传至:"+path;
    }
}
