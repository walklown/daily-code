package com.zzp.learn.springboot.aop1.sensitive;


/**
 * <p> 敏感数据类型
 *
 * @author 【千殇】（【罗玉华】qianshang.luo@tuya.com）
 * @since 2021/8/26 3:27 下午
 */
public enum SensitiveType {
    /**
     * 姓名
     */
    NAME,
    /**
     * 手机
     */
    MOBILE,
    /**
     * 邮箱
     */
    EMAIL,
    /**
     * 身份证
     */
    IDCARD,
    /**
     * 生日
     */
    BIRTHDAY,
    /**
     * 密码
     */
    PASSWORD,
    /**
     * 银行卡
     */
    BANKCARD,
    /**
     * 薪水
     */
    SALARY,
    /**
     * 其他证件号码
     */
    OTHERCARD,
    /**
     * 对象（将对标记为OBJECT的对象会处理该对象里面的字段）
     */
    OBJECT

}
