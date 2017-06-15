package ScreenShot.PaintComponent;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by zangguodong on 2017/6/15.
 */
public class CurrentScreenShot {
    static Toolkit toolkit=Toolkit.getDefaultToolkit();
    static Dimension dimension=toolkit.getScreenSize();
    static int screenHeight= (int) dimension.getHeight();
    static int screenWidth=(int) dimension.getWidth();
    private static BufferedImage getCurrentScreen() throws AWTException {
        Rectangle rectangle=new Rectangle(screenWidth,screenHeight);
        Robot robot=new Robot();
        return robot.createScreenCapture(rectangle);
    }
    private static BufferedImage resize(int w,int h,BufferedImage image){
        int width=image.getWidth();
        int height=image.getHeight();
        BufferedImage newImage=new BufferedImage(w,h,image.getType());
        Graphics2D g=newImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(image,0,0,w,h,0,0,width,height,null);
        g.dispose();
        return newImage;
    }
    public static BufferedImage getCompressImage(int w,int h) throws AWTException {
        return resize(w,h,getCurrentScreen());
    }
}
