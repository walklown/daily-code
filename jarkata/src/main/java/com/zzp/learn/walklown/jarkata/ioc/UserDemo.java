package com.zzp.learn.walklown.jarkata.ioc;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class UserDemo {

    public static void main(String[] args) throws IntrospectionException {
        var a = Byte.valueOf("1");
        var b = 1;
        //自省
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            System.out.println(propertyDescriptor);
        }


        BeanInfo beanInfo1 = Introspector.getBeanInfo(User.class, Object.class);
        PropertyDescriptor[] propertyDescriptors1 = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors1) {
            System.err.println(propertyDescriptor);
        }
    }
}
