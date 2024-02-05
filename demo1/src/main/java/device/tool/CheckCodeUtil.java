package cn.yzw.device.tool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class CheckCodeUtil {
    public static void getCode(OutputStream outputStream, String code){

        int width = 100;
        int height = 38;

        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);

        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,width,height);

        graphics.setColor(Color.BLACK);
        graphics.drawRect(0,0,width-1,height-1);

        graphics.setColor(Color.BLUE);
        graphics.setFont(new Font("宋体",Font.ITALIC, 32));
        graphics.drawString(code,20,30);


        Color[] color = { Color.getHSBColor(127,255,212), Color.getHSBColor(0,255,127),
                Color.getHSBColor(135,206,250), Color.getHSBColor(250,160,122) };
        Random rd = new Random();
        for (int i = 0; i < 20; i++) {
            graphics.setColor(color[rd.nextInt(color.length)]);
            int x1 = rd.nextInt(width);
            int x2 = rd.nextInt(width);
            int y1 = rd.nextInt(height);
            int y2 = rd.nextInt(height);
            graphics.drawLine(x1, y1, x2, y2);
        }

        try {
            ImageIO.write(image, "jpg", outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
