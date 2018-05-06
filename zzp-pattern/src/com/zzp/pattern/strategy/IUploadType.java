package com.zzp.pattern.strategy;

public interface IUploadType {
//    public final  static  Alipay ali_pay = new Alipay();
//    public final IDpay ID_pay = new IDpay();
    State upload(String fileId);
}
