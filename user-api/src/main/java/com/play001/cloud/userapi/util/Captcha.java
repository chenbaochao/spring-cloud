package com.play001.cloud.userapi.util;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

//验证码
public class Captcha {

    //返回验证码bufferImage
    public static BufferedImage createCaptchaImg( String code){
        // 图片高度
        final int IMG_HEIGHT = 30;
        // 图片宽度
        final int IMG_WIDTH = 100;

        // 用于绘制图片，设置图片的长宽和图片类型（RGB)
        BufferedImage bi = new BufferedImage(IMG_WIDTH,IMG_HEIGHT , BufferedImage.TYPE_INT_RGB);
        // 获取绘图工具
        Graphics graphics = bi.getGraphics();
        graphics.setColor(new Color(100, 230, 200)); // 使用RGB设置背景颜色
        graphics.fillRect(0, 0, 100, 30); // 填充矩形区域
        Random random = new Random();
        Font font = new Font("Arial", Font.BOLD, 18);
        graphics.setFont(font);
        for(int i = 0; i < code.length(); i++) { // 循环将每个验证码字符绘制到图片上
            // 随机生成验证码颜色
            graphics.setColor(new Color(random.nextInt(150), random.nextInt(200), random.nextInt(255)));
            // 将一个字符绘制到图片上，并制定位置（设置x,y坐标）
            graphics.drawString(code.charAt(i) + "", (i * 20) + 15, 20);
        }
        return bi;
    }

    public static String randCaptchaCode(){
        // 验证码长度
        final int CODE_LEN = 4;
        char[] codeChar = "ABDEFGHJKMNQRSTWXYabdefghijkmnpqrstuvwxy13456".toCharArray();
        String captcha = ""; // 存放生成的验证码
        Random random = new Random();
        for(int i = 0; i < CODE_LEN; i++) { // 循环将每个验证码字符绘制到图片上
            int index = random.nextInt(codeChar.length);
            captcha += codeChar[index];
        }
        return captcha;
    }
}
