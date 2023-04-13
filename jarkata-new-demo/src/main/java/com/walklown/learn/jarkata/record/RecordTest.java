package com.walklown.learn.jarkata.record;

public class RecordTest {

    public static void main(String[] args) {
        MyRecord myRecord = new MyRecord(1, 2);
        System.out.println(myRecord);
    }
}

record MyRecord(int lo, int hi) {

}
