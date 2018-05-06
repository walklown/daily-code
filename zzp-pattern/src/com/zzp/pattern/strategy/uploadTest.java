package com.zzp.pattern.strategy;

public class uploadTest {
    public static void main(String[] args) {
        Uploader uploader = new Uploader("s121");
        System.out.println(uploader.upload(FileType.excel));
    }
}
