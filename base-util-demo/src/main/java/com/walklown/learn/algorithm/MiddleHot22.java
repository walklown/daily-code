//package com.walklown.learn.algorithm;
//
//import com.walklown.learn.json.JacksonUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 22. 括号生成
// *
// * @author 守愚（张智沛）
// */
//public class MiddleHot22 {
//
//    public static void main(String[] args) {
//        MiddleHot22 middleHot = new MiddleHot22();
//        System.out.println(
//                JacksonUtils.toJSONString(middleHot.generateParenthesis(1)));
//    }
//
//    public List<String> generateParenthesis(int n) {
//        List<String> result = new ArrayList<>();
//        deep(n, result);
//        return result;
//    }
//
//    private List<StringBuilder> deep(int n) {
//        for (int i = 1; i < n; i++) {
//            for (int j = 0; j < i; j++) {
//
//            }
//            StringBuilder current = new StringBuilder();
//            for (int j = 0; j < i; j++) {
//
//            }
//        }
//    }
//}
