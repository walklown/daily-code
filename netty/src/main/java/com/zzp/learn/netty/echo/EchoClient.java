package com.zzp.learn.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.*;

public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //1创建 Bootstrap
            Bootstrap b = new Bootstrap();
            //2指定 EventLoopGroup 来处理客户端事件。由于我们使用 NIO 传输，所以用到了 NioEventLoopGroup 的实现
            b.group(group)
                    //3使用的 channel 类型是一个用于 NIO 传输
                    .channel(NioSocketChannel.class)
                    //4设置服务器的 InetSocketAddress
                    .remoteAddress(new InetSocketAddress(host, port))
                    //5当建立一个连接和一个新的通道时，创建添加到 EchoClientHandler 实例 到 channel pipeline
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(
                                    new EchoClientHandler());
                        }
                    });
            //6连接到远程;等待连接完成
            ChannelFuture f = b.connect().sync();
            //7阻塞直到 Channel 关闭
            f.channel().closeFuture().sync();
        } finally {
            //8调用 shutdownGracefully() 来关闭线程池和释放所有资源
            group.shutdownGracefully().sync();
        }
    }

    private static final ExecutorService executor = Executors.newFixedThreadPool(4);

    private static final CountDownLatch latch = new CountDownLatch(2);
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final String host = "localhost";
        final int port = 8080;

//        Runnable command = () -> {
//            try {
//                new EchoClient(host, port).start();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            latch.countDown();
//        };
//        executor.execute(command);
//        executor.execute(command);
//        latch.await();

        Callable<Integer> callable = () -> {
            try {
                new EchoClient(host, port).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        };
        Future future1 = executor.submit(callable);
        Future future2 = executor.submit(callable);
        future1.get();
        future2.get();
        executor.shutdown();
    }
}
