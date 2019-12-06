package com.zzp.learn.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author shoujing
 * @date 2019/5/12 16:37
 */
public class BioEchoServer {

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
        new BioEchoServer(port).start();
    }

    private final int port;

    public BioEchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        //3创建 EventLoopGroup
        EventLoopGroup bossGroup = new OioEventLoopGroup(1);
        EventLoopGroup workerGroup = new OioEventLoopGroup(1);
        try {
            ServerBootstrap b = new ServerBootstrap();    //1
            b.group(bossGroup, workerGroup)   //2
                    //5指定使用 OIO 的传输 Channel
                    .channel(OioServerSocketChannel.class)
                    //6设置 socket 地址使用所选的端口
                    .localAddress(new InetSocketAddress(port))
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
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
            System.out.println(BioEchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
            //9关闭 channel 和 块，直到它被关闭
            f.channel().closeFuture().sync();
        } finally {
            //10关闭 EventLoopGroup，释放所有资源。
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }
}
