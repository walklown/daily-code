package com.zzp.learn.walklown.jarkata.pattern.strategy;

public enum FileType {
    office(new Office()), excel(new Excel());

    private IUploadType fileUpload;

    FileType(IUploadType fileUpload) {
        this.fileUpload = fileUpload;
    }

    public IUploadType getUploadType() {
        return fileUpload;
    }
}
