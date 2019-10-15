package com.zzp.learn.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author shoujing
 * @date 2019/5/12 16:37
 */
public class EchoServer {

    public static void main(String[] args) throws Exception {
//        if (args.length != 1) {
//            System.err.println(
//                    "Usage: " + EchoServer.class.getSimpleName() +
//                            " <port>");
//            return;
//        }
        //设置端口值（抛出一个 NumberFormatException 如果该端口参数的格式不正确）
        int port = 8080;
        //呼叫服务器的 start() 方法
        new EchoServer(port).start();
    }

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        //3创建 EventLoopGroup
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            //4创建 ServerBootstrap
            b.group(group)
                    //5指定使用 NIO 的传输 Channel
                    .channel(NioServerSocketChannel.class)
                    //6设置 socket 地址使用所选的端口
                    .localAddress(new InetSocketAddress(port))
                    //7添加 EchoServerHandler 到 Channel 的 ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(
                                    new EchoServerHandler());
                        }
                    });
            //8绑定的服务器;sync 等待服务器关闭
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
            //9关闭 channel 和 块，直到它被关闭
            f.channel().closeFuture().sync();
        } finally {
            //10关闭 EventLoopGroup，释放所有资源。
            group.shutdownGracefully().sync();
        }
    }
}
