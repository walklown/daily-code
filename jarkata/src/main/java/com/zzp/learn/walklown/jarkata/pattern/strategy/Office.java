package com.zzp.learn.walklown.jarkata.pattern.strategy;

public class Office implements IUploadType {
    @Override
    public State upload(String fileId) {
        System.out.println("上传Office文件");
        return new State("200", fileId, "C:/Office");
    }
}
