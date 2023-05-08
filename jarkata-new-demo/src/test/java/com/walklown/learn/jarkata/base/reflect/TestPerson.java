package com.walklown.learn.jarkata.base.reflect;

public class TestPerson {

    public String name;

    private String mobile;

    public TestPerson() {
    }

    public TestPerson(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String getMobile() {
        return mobile;
    }

    private String privateGetMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    private void sleep(String time) {
        System.out.println("sleeping:" + time);
    }

    public static String transfer(String param) {
        System.out.println("params:" + param);
        return param;
    }
}
