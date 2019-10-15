package com.zzp.learn.walklown.jarkata.pattern.strategy;

public class Uploader {
    private String fileId;

    public Uploader(String fileId) {
        this.fileId = fileId;
    }

    public State upload(FileType fileType) {
        return fileType.getUploadType().upload(fileId);
    }
}
