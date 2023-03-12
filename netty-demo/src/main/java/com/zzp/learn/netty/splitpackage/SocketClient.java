package com.zzp.learn.netty.splitpackage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        // 要连接的服务端IP地址和端口
        String host = "127.0.0.1";
        int port = 55533;
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        // 建立连接后获得输出流
        OutputStream outputStream = socket.getOutputStream();
        String message = "这是一个整包!!!";
        byte[] contentBytes = message.getBytes("UTF-8");
        System.out.println("contentBytes.length = " + contentBytes.length);
        int length = contentBytes.length;
        byte[] lengthBytes = Utils.int2Bytes(length);
        byte[] resultBytes = new byte[4 + length];
        System.arraycopy(lengthBytes, 0, resultBytes, 0, lengthBytes.length);
        System.arraycopy(contentBytes, 0, resultBytes, 4, contentBytes.length);

        for (int i = 0; i < 20; i++) {
            outputStream.write(resultBytes);
//            outputStream.write(contentBytes);
        }
        Thread.sleep(20000);
        outputStream.close();
        socket.close();
    }


}

final class Utils {
    //int数值转为字节数组
    public static byte[] int2Bytes(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) (i >> 24 & 0xFF);
        result[1] = (byte) (i >> 16 & 0xFF);
        result[2] = (byte) (i >> 8 & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }
    //字节数组转为int数值
    public static int bytes2Int(byte[] bytes){
        int num = bytes[3] & 0xFF;
        num |= ((bytes[2] << 8) & 0xFF00);
        num |= ((bytes[1] << 16) & 0xFF0000);
        num |= ((bytes[0] << 24)  & 0xFF000000);
        return num;
    }
}
