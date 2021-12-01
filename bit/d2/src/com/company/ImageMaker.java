package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ImageMaker {

    //bad code
    public static void main(String[] args) throws Exception {

        BufferedImage bufferedImage = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);

        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.PINK);
        graphics.fillRect(0, 0, 300, 300);

        int value = (int) (Math.random() * 10000);
        graphics.setColor(Color.BLACK);

        graphics.setFont((new Font("TimesRoman", Font.BOLD, 40)));
        graphics.drawString("" + value, 10, 140);

        OutputStream fileOutputStream = new FileOutputStream("/Users/kimjinwook/zzz/test.gif");

        ImageIO.write(bufferedImage, "gif", fileOutputStream);

    }
}
