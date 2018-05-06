package com.zzp.pattern.strategy;

public class Uploader {
    private String fileId;

    public Uploader(String fileId) {
        this.fileId = fileId;
    }

    public State upload(FileType fileType){
        return fileType.getUploadType().upload(fileId);
    }
}
