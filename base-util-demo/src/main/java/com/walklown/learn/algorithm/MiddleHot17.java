package com.walklown.learn.algorithm;

import com.walklown.learn.json.JacksonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 *
 * @author 守愚（张智沛）
 */
public class MiddleHot17 {

    public static void main(String[] args) {
        MiddleHot17 middleHot = new MiddleHot17();
        System.out.println(JacksonUtils.toJSONString(middleHot.letterCombinations("23")));
    }

    private static final List<String> map = new ArrayList<>() {{
        add("abc");
        add("def");
        add("ghi");
        add("jkl");
        add("mno");
        add("pqrs");
        add("tuv");
        add("wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }
        char[] value = new char[digits.length()];
        List<String> list = new ArrayList<>();
        deep(0, value, digits, list);
        return list;
    }

    private void deep(int offset, char[] value, String digits, List<String> list) {
        int number = digits.charAt(offset) - 50;
        String letters = map.get(number);
        if (offset == digits.length() - 1) {
            // 最后一次
            for (char current : letters.toCharArray()) {
                value[offset] = current;
                String letterCombination = String.valueOf(value);
                list.add(letterCombination);
            }
        } else {
            for (char current : letters.toCharArray()) {
                value[offset] = current;
                deep(offset + 1, value, digits, list);
            }
        }
    }

}
