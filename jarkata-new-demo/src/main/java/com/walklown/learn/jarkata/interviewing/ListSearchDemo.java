package com.walklown.learn.jarkata.interviewing;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * //题目2
 * // 假设有个列表里存储了中国所有的6位地区编码，String 类型， 内容是 330101,西湖区 330000,浙江省
 * // 要求提供一个方法，可以快速的找到 给定地区所
 * // 如 浙江省: 330000, 杭州: 330100, 西湖区：330101
 * // 上海市 310000, 上海市：310100， 闸北区: 310105
 * // 如果 输入：330101， 能查到是 返回浙江省杭州市西湖区
 *
 * @author shoujing
 * @date 2019/12/10 22:39
 */
public class ListSearchDemo {

    private static final List<String> AREA_CODE_LIST = new LinkedList<>() {{
        add("330101,西湖区");
        add("330100,杭州");
        add("330000,浙江省");
    }};

    private static final Map<String, Node> NODES = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        for (String s : AREA_CODE_LIST) {
            String[] str = s.split(",");
            String provinceCode = str[0].substring(0, 2);
            if (str[0].substring(2, 6).equals("0000")) {
                Node provinceNode = NODES.get(provinceCode);
                if (provinceNode != null) {
                    provinceNode.setName(str[1]);
                } else {
                    Node node = new Node();
                    node.setCode(str[0].substring(0, 2));
                    node.setName(str[1]);
                    NODES.put(provinceCode, node);
                }
                continue;
            }
            String cityCode = str[0].substring(2, 4);
            if (str[0].substring(4, 6).equals("00")) {
                Node provinceNode = NODES.get(provinceCode);
                if (provinceNode != null) {
                    Node cityNode = provinceNode.getChild().get(cityCode);
                    if (cityNode != null) {
                        cityNode.setName(str[1]);
                        continue;
                    }
                }
                if (provinceNode == null) {
                    provinceNode = new Node();
                    provinceNode.setCode(str[0].substring(0, 2));
                    NODES.put(provinceCode, provinceNode);
                }
                Node node = new Node();
                node.setCode(str[0].substring(2, 4));
                node.setName(str[1]);
                provinceNode.getChild().put(cityCode, node);
                continue;
            }
            String areaCode = str[0].substring(4, 6);
            Node provinceNode = NODES.get(provinceCode);
            Node cityNode = null;
            if (provinceNode != null) {
                cityNode = provinceNode.getChild().get(cityCode);
                if (cityNode != null) {
                    Node areaNode = cityNode.getChild().get(areaCode);
                    if (areaNode != null) {
                        cityNode.setName(str[1]);
                        continue;
                    }
                }
            }
            if (provinceNode == null) {
                provinceNode = new Node();
                provinceNode.setCode(provinceCode);
                NODES.put(provinceCode, provinceNode);
            }
            if (cityNode == null) {
                cityNode = new Node();
                cityNode.setCode(cityCode);
                provinceNode.getChild().put(cityCode, cityNode);
            }
            Node node = new Node();
            node.setCode(areaCode);
            node.setName(str[1]);
            cityNode.getChild().put(areaCode, node);
            continue;
        }
        System.out.println(searchArea("330101"));
    }

    public static String searchArea(String code) {
        StringBuilder result = new StringBuilder("");
        Node provinceNode = NODES.get(code.substring(0, 2));
        if (provinceNode != null) {
            result.append(provinceNode.getName());
            Node cityNode = provinceNode.getChild().get(code.substring(2, 4));
            if (cityNode != null) {
                result.append(cityNode.getName());
                Node areaNode = cityNode.getChild().get(code.substring(4, 6));
                if (areaNode != null) {
                    result.append(areaNode.getName());
                }
            }
        }
        return result.toString();
    }
}

class Node implements Comparable<Node> {

    private String code;

    private String name;

    private Map<String, Node> child;

    public Node() {
        this.child = new HashMap<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Node> getChild() {
        return child;
    }

    public void setChild(Map<String, Node> child) {
        this.child = child;
    }

    @Override
    public int compareTo(Node o) {
        if (o == this) {
            return 0;
        }
        if (o == null) {
            return -1;
        }
        return this.code.compareTo(o.code);
    }
}