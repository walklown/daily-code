package com.zzp.learn.springboot.aop1.sensitive;


import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * <p> 数据隐私显示 手机号，身份证号和银行卡号等
 *
 * @author 【千殇】（【罗玉华】qianshang.luo@tuya.com）
 * @since 2021/8/26 3:38 下午
 */
public class SensitiveUtils {

    /**
     * 手机号
     * 手机号留前4位和后4位，其他*
     *
     * @param phone
     * @return
     */
    public static String maskMobile(String phone) {
        if (StringUtils.isBlank(phone)) {
            return "";
        }
        if (StringUtils.length(phone) < 8) {
            return phone;
        }
        int start = 4;
        int end = phone.length() - 4;
        StringBuffer overPlay = new StringBuffer();
        for (int i = start; i < end; i++) {
            overPlay.append("*");
        }
        return StringUtils.overlay(phone, overPlay.toString(), start, end);
    }

    /**
     * 邮箱账号
     * 邮箱留前4位及@符号以后，其他*
     *
     * @param email
     * @return
     */
    public static String maskEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return "";
        }
        String at = "@";
        if (!email.contains(at)) {
            return email;
        }
        /**
         * 这里主要逻辑是需要保留邮箱的注册商 比如@qq.com
         * 后四位打码，不足四位,除了@前都打码
         */
        int index = StringUtils.indexOf(email, at);
        String content = StringUtils.substring(email, 0, index);
        String mask = "";
        if (content.length() > 4) {
            int start = 4;
            int end = index;
            StringBuffer overPlay = new StringBuffer();
            for (int i = start; i < end; i++) {
                overPlay.append("*");
            }
            mask = StringUtils.overlay(content, overPlay.toString(), 4, content.length());
        } else {
            int start = 0;
            int end = index;
            StringBuffer overPlay = new StringBuffer();
            for (int i = start; i < end; i++) {
                overPlay.append("*");
            }
            mask = overPlay.toString();
        }
        return mask + StringUtils.substring(email, index);
    }

    /**
     * 身份证打码操作
     * 身份证留前4位和后3位，其他 *
     *
     * @param idCard
     * @return
     */
    public static String maskIdCard(String idCard) {
        if (StringUtils.isBlank(idCard)) {
            return "";
        }
        if (StringUtils.length(idCard) < 7) {
            return idCard;
        }
        int start = 4;
        int end = idCard.length() - 3;
        StringBuffer overPlay = new StringBuffer();
        for (int i = start; i < end; i++) {
            overPlay.append("*");
        }
        return StringUtils.overlay(idCard, overPlay.toString(), start, end);
    }

    /**
     * 生日打码操作
     * 中间月日打码。生日年月日
     *
     * @param birthday
     * @return
     */
    public static String maskBirthday(String birthday) {
        if (StringUtils.isBlank(birthday)) {
            return "";
        }
        if (StringUtils.length(birthday) <= 4) {
            return birthday;
        }
        String pre = birthday.substring(0, 4);
        String suf = birthday.substring(4);
        String sufResult = StringUtils.replaceAll(suf, "[0-9]", "*");
        return pre + sufResult;
    }

    /**
     * 银行卡号
     * 银行账号留前4位和后4位，其他*
     *
     * @param bandCard
     * @return
     */
    public static String maskBankCard(String bandCard) {
        if (StringUtils.isBlank(bandCard)) {
            return "";
        }
        if (StringUtils.length(bandCard) < 8) {
            return bandCard;
        }
        int start = 4;
        int end = bandCard.length() - 4;
        StringBuffer overPlay = new StringBuffer();
        for (int i = start; i < end; i++) {
            overPlay.append("*");
        }
        return StringUtils.overlay(bandCard, overPlay.toString(), start, end);
    }

    /**
     * 密码全部打码
     *
     * @param password
     * @return
     */
    public static String maskPassword(String password) {
        if (StringUtils.isBlank(password)) {
            return "";
        }
        int end = password.length();
        StringBuffer overPlay = new StringBuffer();
        for (int i = 0; i < end; i++) {
            overPlay.append("*");
        }
        return StringUtils.overlay(password, overPlay.toString(), 0, end);
    }

    /**
     * 中文姓名，除了第一位不打码
     *
     * @param name
     * @return
     */
    public static String maskName(String name) {
        if (StringUtils.isBlank(name)) {
            return "";
        }
        int end = name.length();
        StringBuffer overPlay = new StringBuffer();
        for (int i = 1; i < end; i++) {
            overPlay.append("*");
        }
        return StringUtils.overlay(name, overPlay.toString(), 1, end);
    }

    /**
     * 月薪，全部*
     *
     * @param salary
     * @return
     */
    public static String maskSalary(String salary) {
        if (StringUtils.isBlank(salary)) {
            return "";
        }
        int end = salary.length();
        StringBuffer overPlay = new StringBuffer();
        for (int i = 0; i < end; i++) {
            overPlay.append("*");
        }
        return StringUtils.overlay(salary, overPlay.toString(), 0, end);
    }

    /**
     * 其他证件号码前1位和后3位其他全部为*
     *
     * @param otherCard
     * @return
     */
    public static String maskOtherCard(String otherCard) {
        if (StringUtils.isBlank(otherCard)) {
            return "";
        }
        if (StringUtils.length(otherCard) < 4) {
            return otherCard;
        }
        int start = 1;
        int end = otherCard.length() - 3;
        StringBuffer overPlay = new StringBuffer();
        for (int i = start; i < end; i++) {
            overPlay.append("*");
        }
        return StringUtils.overlay(otherCard, overPlay.toString(), start, end);
    }


    /**
     * 对list结果集支持
     *
     * @param list
     * @param <T>
     * @throws Exception
     */
    public static <T> void supportList(List<T> list) {
        for (T t : list) {
            try {
                SensitiveHandler.handle(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对object结果集支持
     *
     * @param t
     * @param <T>
     * @throws Exception
     */
    public static <T> void supportObject(T t) {
        try {
            SensitiveHandler.handle(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
