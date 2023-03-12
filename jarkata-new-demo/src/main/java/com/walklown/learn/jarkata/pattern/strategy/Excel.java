package com.walklown.learn.jarkata.pattern.strategy;

public class Excel implements IUploadType {
    @Override
    public State upload(String fileId) {
        System.out.println("上传Excel文件");
        return new State("200", fileId, "C:/Excel");
    }
}
