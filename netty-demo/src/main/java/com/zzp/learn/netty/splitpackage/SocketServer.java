package com.zzp.learn.netty.splitpackage;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws Exception {
        // 监听指定的端口
        int port = 55533;
        ServerSocket server = new ServerSocket(port);
        // server将一直等待连接的到来
        System.out.println("server将一直等待连接的到来");
        Socket socket = server.accept();
        // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024 * 128];
        int len;
        byte[] totalBytes = new byte[]{};
        int totalLength = 0;
        while ((len = inputStream.read(bytes)) != -1) {
            //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
//            String content = new String(bytes, 0, len,"UTF-8");
//            System.out.println("len = " + len + ", content: " + content);
            //1. 将读取的数据和上一次遗留的数据拼起来
            int tempLength = totalLength;
            totalLength = len + totalLength;
            byte[] tempBytes = totalBytes;
            totalBytes = new byte[totalLength];
            System.arraycopy(tempBytes, 0, totalBytes, 0, tempLength);
            System.arraycopy(bytes, 0, totalBytes, tempLength, len);
            while (totalLength > 4) {
                byte[] lengthBytes = new byte[4];
                System.arraycopy(totalBytes, 0, lengthBytes, 0, lengthBytes.length);
                int contentLength = Utils.bytes2Int(lengthBytes);
                //2. 如果剩下数据小于数据头标的长度，则出现拆包，再次获取数据连接
                if (totalLength < contentLength + 4) {
                    break;
                }
                //3. 将数据头标的指定长度的数据取出则为应用数据
                byte[] contentBytes = new byte[contentLength];
                System.arraycopy(totalBytes, 4, contentBytes, 0, contentLength);
                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                String content = new String(contentBytes, "UTF-8");
                System.out.println("contentLength = " + contentLength + ", content: " + content);
                //4. 去掉已读取的数据
                totalLength -= (4 + contentLength);
                byte[] leftBytes = new byte[totalLength];
                System.arraycopy(totalBytes, 4 + contentLength, leftBytes, 0, totalLength);
                totalBytes = leftBytes;
            }
        }
        inputStream.close();
        socket.close();
        server.close();
    }
}