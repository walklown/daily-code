package com.zzp.learn.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * echo 服务器
 *
 * @author shoujing
 * @date 2019/5/12 16:21
 */
//@Sharable 标识这类的实例之间可以在 channel 里面共享
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 每个信息入站都会调用
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx,
                            Object msg) {
        ByteBuf in = (ByteBuf) msg;
        //日志消息输出到控制台pro
        System.out.println(String.format("%s-Server received: %s", Thread.currentThread().getName(), in.toString(CharsetUtil.UTF_8)));
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //将所接收的消息返回给发送者。注意，这还没有Flush数据
        ctx.write(in);
    }

    /**
     * 通知处理器最后的 channelRead() 是当前批处理中的最后一条消息时调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println(String.format("%s-Server Complete", Thread.currentThread().getName()));
        //Flush所有待审消息到远程节点。关闭通道后，操作完成
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 读操作时捕获到异常时调用
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        //打印异常堆栈跟踪
        cause.printStackTrace();
        //关闭通道
        ctx.close();
    }
}



