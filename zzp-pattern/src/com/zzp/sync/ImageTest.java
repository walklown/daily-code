package com.zzp.sync;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageTest {
    public static void main(String[] args) {
        File out = new File("E:/pictures/游星x里想奈.PNG");
        //将图片写入ImageIO流
        try {
            BufferedImage img = ImageIO.read(out);
            //将图片写出到指定位置（复制图片）
            OutputStream ops = new FileOutputStream(new File("E:/test.png"));
            ImageIO.write(img, "png", ops);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
