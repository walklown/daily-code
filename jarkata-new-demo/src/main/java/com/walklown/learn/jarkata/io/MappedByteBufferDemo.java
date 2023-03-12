package com.walklown.learn.jarkata.io;

import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class MappedByteBufferDemo {
    public static void main(String[] args) {
        try (FileChannel fileChannel = new RandomAccessFile(new File("D://db.data"), "rw").getChannel()) {
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());
            int position = 8;
            // 写
            byte[] data = new byte[4];
            data[0] = 0;
            data[1] = 1;
            data[2] = 2;
            data[3] = 3;
            // 从当前 mmap 指针的位置写入 4b 的数据
            mappedByteBuffer.put(data);
            // 指定 position 写入 4b 的数据
            ByteBuffer subBuffer = mappedByteBuffer.slice();
            subBuffer.position(position);
            subBuffer.put(data);

            // 读
            byte[] data1 = new byte[4];
            // 从当前 mmap 指针的位置读取 4b 的数据
            mappedByteBuffer.get(data);
            // 指定 position 读取 4b 的数据
            subBuffer.position(position);
            subBuffer.get(data1);

            clean(mappedByteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clean(MappedByteBuffer mappedByteBuffer) {
        ByteBuffer buffer = mappedByteBuffer;
        if (buffer == null || !buffer.isDirect() || buffer.capacity()== 0)
            return;
        invoke(invoke(viewed(buffer), "cleaner"), "clean");
    }

    private static Object invoke(final Object target, final String methodName, final Class<?>... args) {
        return AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                try {
                    Method method = method(target, methodName, args);
                    method.setAccessible(true);
                    return method.invoke(target);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        });
    }

    private static Method method(Object target, String methodName, Class<?>[] args)
            throws NoSuchMethodException {
        try {
            return target.getClass().getMethod(methodName, args);
        } catch (NoSuchMethodException e) {
            return target.getClass().getDeclaredMethod(methodName, args);
        }
    }

    private static ByteBuffer viewed(ByteBuffer buffer) {
        String methodName = "viewedBuffer";
        Method[] methods = buffer.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals("attachment")) {
                methodName = "attachment";
                break;
            }
        }
        ByteBuffer viewedBuffer = (ByteBuffer) invoke(buffer, methodName);
        if (viewedBuffer == null)
            return buffer;
        else
            return viewed(viewedBuffer);
    }
}
