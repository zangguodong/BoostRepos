package testgetScreen;

import ScreenShot.Network.ImageNet;
import ScreenShot.PaintComponent.CurrentScreenShot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Base64;

/**
 * Created by zangguodong on 2017/6/15.
 */
public class testSend {
    public testSend() throws IOException {
    }

    public static void sendImage( BufferedImage image) throws IOException {
        Socket socket=new Socket("localhost",9000);
        Base64.Encoder encoder=Base64.getEncoder();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        ImageIO.write(image,"jpg", stream);
        byte[] data=stream.toByteArray();
        socket.getOutputStream().write(data);
    }
    public static void sendCurrentImage() throws AWTException, IOException {
        sendImage(CurrentScreenShot.getCompressImage(CurrentScreenShot.screenWidth/2,CurrentScreenShot.screenHeight/2));
    }
    public static void main(String arg[]) throws IOException, AWTException {
        ImageNet imageNet=new ImageNet();
        Robot robot=new Robot();
        DatagramSocket socket=new DatagramSocket(8090);
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                try {
                    sendCurrentImage();
                } catch (AWTException e) {
                    e.printStackTrace();
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }).start();
        new Thread(()->{
            while (true) {
                byte[] data = new byte[1000];
                DatagramPacket packet = new DatagramPacket(data, 0, data.length);
                try {
                    socket.receive(packet);
                    byte[] dat = packet.getData();
                    String str = new String(dat,0,dat.length).trim();
                    System.out.println(str);
                    if(str.startsWith("MouseEvent")){
                        int indsharp=str.indexOf('#');
                        int x=Integer.parseInt(str.substring(10,indsharp));
                        int y=Integer.parseInt(str.substring(indsharp+1));
                        robot.mouseMove(x,y);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
