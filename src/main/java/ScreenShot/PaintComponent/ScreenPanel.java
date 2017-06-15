package ScreenShot.PaintComponent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zangguodong on 2017/6/15.
 */
public class ScreenPanel {
    public static void main(String arg[]) throws AWTException {
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
}
