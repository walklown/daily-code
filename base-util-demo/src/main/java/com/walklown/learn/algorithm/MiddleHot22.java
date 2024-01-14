package com.walklown.learn.algorithm;

import com.walklown.learn.json.JacksonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 *
 * @author 守愚（张智沛）
 */
public class MiddleHot22 {

    public static void main(String[] args) {
        MiddleHot22 middleHot = new MiddleHot22();
        System.out.println(
                JacksonUtils.toJSONString(middleHot.generateParenthesis(3)));
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            result.add("");
            return result;
        }
        if (n == 1) {
            result.add("()");
            return result;
        }
        for (int i = 0; i < n; i++) {
            for (String inner : generateParenthesis(i)) {
                for (String right : generateParenthesis(n - i - 1)) {
                    result.add("(" + inner + ")" + right);
                }
            }
        }
        return result;
    }
}
