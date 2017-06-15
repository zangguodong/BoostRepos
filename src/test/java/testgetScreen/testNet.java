package testgetScreen;

import ScreenShot.PaintComponent.CurrentScreenShot;
import ScreenShot.PaintComponent.ScreenPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

import static java.net.InetAddress.getAllByName;

/**
 * Created by zangguodong on 2017/6/15.
 */
public class testNet {
    static final String MouseEvent="MouseEvent";
    public static void main(String arg[]) throws IOException {
        DatagramSocket socket=new DatagramSocket();
        JFrame jFrame=new JFrame();
        JLabel label=new JLabel();
        jFrame.setSize(CurrentScreenShot.screenWidth/2,CurrentScreenShot.screenHeight/2);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
        jFrame.add(label);
        jFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked( MouseEvent e ) {
                    int x = e.getX()*2;
                    int y = e.getY()*2;
                try {
                    byte[] data=(MouseEvent+x+"#"+y).getBytes();
                    DatagramPacket packet=new DatagramPacket(data,0,data.length,InetAddress.getByName("localhost"),8090);
                    socket.send(packet);
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        ServerSocket serverSocket=new ServerSocket(9000);
        while (true) {
            Socket s = serverSocket.accept();
            System.out.println("we have connection");
            new Thread(() -> {
                try {
                    DataInputStream inputStream = new DataInputStream(s.getInputStream());
                    BufferedImage image=ImageIO.read(inputStream);
                    System.out.println("we made new image");
                    label.setIcon(new ImageIcon(image));
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }).start();
        }
    }
}
