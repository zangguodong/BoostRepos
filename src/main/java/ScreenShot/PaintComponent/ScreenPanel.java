package ScreenShot.PaintComponent;

import ScreenShot.Network.ImageNet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by zangguodong on 2017/6/15.
 */
public class ScreenPanel {
    public static void main2(String arg[]) throws AWTException {
        JFrame jf=new JFrame("testConsole");
         JLabel label=new JLabel();
         jf.setSize(CurrentScreenShot.screenWidth/2,CurrentScreenShot.screenHeight/2);
         jf.setDefaultCloseOperation(3);
         jf.setVisible(true);
         jf.add(label);
         
         new Thread(()->{
             while (true){
                 try {
                     label.setIcon(new ImageIcon(CurrentScreenShot.getCompressImage(jf.getWidth(),jf.getHeight())));
                 } catch (AWTException e) {
                     e.printStackTrace();
                 }
             }
         }).start();
    }
    public static void generateOnceView( BufferedImage image){
        JFrame jf=new JFrame("testConsole");
        JLabel label=new JLabel();
        jf.setSize(CurrentScreenShot.screenWidth/2,CurrentScreenShot.screenHeight/2);
        jf.setDefaultCloseOperation(3);
        jf.setVisible(true);
        jf.add(label);
        label.setIcon(new ImageIcon(image));
    }

}
