package face;

/**
 * @author Breon
 * @description: TODO
 * @date: 2022/12/16 15:38
 */
import javax.swing.*;
import java.awt.*;

import static sun.swing.SwingUtilities2.drawString;

public class test1VideoCapture extends JFrame {


    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setBounds(10,10,900,720);
        frame.setTitle("身份证人脸比对系统");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel=new FaceVerificationPanel();
        frame.add(panel);
        frame.setVisible(true);
    }

}

