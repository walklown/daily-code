package com.walklown.learn.jarkata.pattern.observice.mouse;

import com.walklown.learn.jarkata.pattern.observice.core.MouseHandler;

import java.lang.reflect.Proxy;

/**
 * Created by Tom on 2018/3/17.
 */
public class MouseTest {

    public static void main(String[] args) {

        /*
         * var input = document.getElementById("username");
         * input.addLisenter("click",function(){
         *
         *     alert("鼠标点击了这个文本框");
         *
         * });
         *
         *
         * */

        //观察者和被观察者之间没有必然联系
        //注册的时候，才产生联系


        //解耦


        try {
//            MouseEventCallback callback = new MouseEventCallback();
//            Method onClick = MouseEventCallback.class.getMethod("onClick", Event.class);


            //人为的调用鼠标的单击事件
            IMouse mouse = (IMouse) Proxy.newProxyInstance(Mouse.class.getClassLoader(),
                    Mouse.class.getInterfaces(), new MouseHandler(new Mouse()));
//            mouse.addLisenter(MouseEventType.ON_CLICK, callback,onClick);

            mouse.click();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
