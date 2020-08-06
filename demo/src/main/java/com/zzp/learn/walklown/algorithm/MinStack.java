package com.zzp.learn.walklown.algorithm;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shoujing
 * @date 2020/5/12 20:57
 */
public class MinStack {

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-1);
        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.top());
        System.out.println(obj.getMin());
    }

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        Node newNode = new Node(x);
        if (first == null || x < first.lastMin.i) {
            newNode.lastMin = newNode;
        } else {
            newNode.lastMin = first.lastMin;
        }
        newNode.last = first;
        first = newNode;
    }

    public void pop() {
        first = first.last;
    }

    public int top() {
        return first.i;
    }

    public int getMin() {
        return first.lastMin.i;
    }

    private Node first = null;

    public class Node {

        public Node(int i) {
            this.i = i;
        }

        private int i;

        private Node last;

        private Node lastMin;
    }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */