package com.zzp.learn.springboot.aop1.sensitive;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

/**
 * <p> 核心处理类
 *
 * @author 【千殇】（【罗玉华】qianshang.luo@tuya.com）
 * @since 2021/8/26 10:34 下午
 */
public class SensitiveHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveHandler.class);


    public static void handle(Object obj) throws Exception {
        if (null != obj) {
            Class<?> objClazz = obj.getClass();
            if (null != objClazz) {
                List<Field> allFieldsList = FieldUtils.getAllFieldsList(objClazz);
                if (CollectionUtils.isNotEmpty(allFieldsList)) {
                    for (Field declaredField : allFieldsList) {
                        declaredField.setAccessible(true);
                        SensitiveField sensitiveField = declaredField.getAnnotation(SensitiveField.class);
                        if (null != sensitiveField) {
                            Object fieldVal = declaredField.get(obj);
                            if (null != fieldVal && !Objects.equals(fieldVal.getClass().getName(), obj.getClass().getName())) {
                                if (SensitiveType.OBJECT.equals(sensitiveField.value())) {
                                    // 不能进行递归调用 否则出现oom 死循环
                                    handle(fieldVal);
                                } else {
                                    // 处理
                                    try {
                                        String valStr = (String) fieldVal;
                                        String result = handleSensitiveString(sensitiveField.value(), valStr);
                                        declaredField.set(obj, result);
                                    } catch (Exception e) {
                                        LOGGER.warn("处理返回数据动态脱敏 出现异常", e);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * <p> 处理需要加密的字段
     *
     * @param type
     * @param val
     * @return java.lang.String
     * @author [千殇] ([罗玉华]qianshang.luo@tuya.com)
     * @date 2021/9/8 3:51 下午
     */
    public static String handleSensitiveString(SensitiveType type, String val) {
        try {
            String result = "";
            switch (type) {
                case NAME:
                    result = SensitiveUtils.maskName(val);
                    break;
                case MOBILE:
                    result = SensitiveUtils.maskMobile(val);
                    break;
                case EMAIL:
                    result = SensitiveUtils.maskEmail(val);
                    break;
                case IDCARD:
                    result = SensitiveUtils.maskIdCard(val);
                    break;
                case BIRTHDAY:
                    result = SensitiveUtils.maskBirthday(val);
                    break;
                case PASSWORD:
                    result = SensitiveUtils.maskPassword(val);
                    break;
                case BANKCARD:
                    result = SensitiveUtils.maskBankCard(val);
                    break;
                case SALARY:
                    result = SensitiveUtils.maskSalary(val);
                    break;
                case OTHERCARD:
                    result = SensitiveUtils.maskOtherCard(val);
                    break;
                default:
                    result = val;
                    break;
            }
            return result;
        } catch (Exception e) {
            LOGGER.warn("处理脱敏字段出现异常", e);
            return val;
        }
    }

}
