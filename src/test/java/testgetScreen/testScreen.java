package testgetScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Created by zangguodong on 2017/6/15.
 */
public class testScreen {
    public static void main(String []ag) throws AWTException {
        JFrame jFrame=new JFrame("控制台");

        JLabel label=new JLabel();
        jFrame.add(label);
        jFrame.setVisible(true);
        jFrame.setAlwaysOnTop(true);
        jFrame.setDefaultCloseOperation(3);
        Toolkit toolkit=Toolkit.getDefaultToolkit();
        Dimension dimension=toolkit.getScreenSize();
        int totalHeight= (int) dimension.getHeight();
        int totalWidth= (int) dimension.getWidth();
        System.out.println(totalHeight+" "+totalWidth);
        jFrame.setSize(totalWidth/2,totalHeight/2);
        Robot robot=new Robot();
        jFrame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked( MouseEvent e ) {
                int nowx=e.getX();
                int nowy=e.getY();
                robot.mouseMove(nowx*2,nowy*2);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }

            @Override
            public void mousePressed( MouseEvent e ) {

            }

            @Override
            public void mouseReleased( MouseEvent e ) {

            }

            @Override
            public void mouseEntered( MouseEvent e ) {

            }

            @Override
            public void mouseExited( MouseEvent e ) {

            }
        });
        new Thread(()->{
            while (true){
                Rectangle rectangle=new Rectangle(0,0,(int)dimension.getWidth(),(int)dimension.getHeight());
                BufferedImage image=robot.createScreenCapture(rectangle);
                BufferedImage little=resize(image,jFrame.getWidth(),jFrame.getHeight());
                label.setIcon(new ImageIcon(little));
            }
        }).start();
    }
    public static BufferedImage resize(BufferedImage img,int w,int h){
        int wid=img.getWidth();
        int hei=img.getHeight();
        BufferedImage dimg=new BufferedImage(w,h,img.getType());
        Graphics2D g=dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img,0,0,w,h,0,0,wid,hei,null);
        g.dispose();
        return dimg;
    }
}
