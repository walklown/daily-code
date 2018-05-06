package com.zzp.pattern.observice.mouse;


/**
 * 被观察者
 * 如果做过Swing开发的话，有一种似曾相识的感觉
 * Created by Tom on 2018/3/17.
 */
//public class Mouse extends EventLisenter {
public class Mouse implements IMouse {

    @Override
    public void click(){
        System.out.println("鼠标单击");
//        this.trigger(MouseEventType.ON_CLICK);
    }

    @Override
    public void doubleClick(){
        System.out.println("鼠标双击");
//        this.trigger(MouseEventType.ON_DOUBLE_CLICK);
    }

    @Override
    public void up(){
        System.out.println("鼠标弹起");
//        this.trigger(MouseEventType.ON_UP);
    }

    @Override
    public void down(){
        System.out.println("鼠标按下");
//        this.trigger(MouseEventType.ON_DOWN);
    }

    @Override
    public void wheel(){
        System.out.println("鼠标滚动");
//        this.trigger(MouseEventType.ON_WHEEL);
    }

    @Override
    public void move(){
        System.out.println("鼠标移动");
//        this.trigger(MouseEventType.ON_MOVE);
    }

    @Override
    public void over(){
        System.out.println("鼠标悬停");
//        this.trigger(MouseEventType.ON_OVER);
    }
}
