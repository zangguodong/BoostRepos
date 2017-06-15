package ScreenShot.Network;

import ScreenShot.PaintComponent.CurrentScreenShot;
import com.sun.xml.internal.ws.util.ByteArrayBuffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.stream.Stream;

/**
 * Created by zangguodong on 2017/6/15.
 */
public class ImageNet {

    final Socket socket=new Socket("localhost",9000);
    public ImageNet() throws IOException {
    }
    public void sendImage( BufferedImage image) throws IOException {
        Base64.Encoder encoder=Base64.getEncoder();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        ImageIO.write(image,"jpg", stream);
        byte[] data=stream.toByteArray();
        socket.getOutputStream().write(data);
        socket.getOutputStream().flush();
    }
    public void sendCurrentImage() throws AWTException, IOException {
        sendImage(CurrentScreenShot.getCompressImage(500,400));
    }

}
