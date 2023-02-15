package face;

/**
 * @author Breon
 * @description: TODO
 * @date: 2022/12/16 15:49
 */
import com.github.sarxos.webcam.Webcam;

import java.awt.*;
import java.awt.image.BufferedImage;

public  class RunVideoThread implements Runnable{

    boolean flag;
    Webcam webcam;
    Graphics g;

    public RunVideoThread(Webcam webcam, Graphics g) {
        this.webcam = webcam;
        this.g = g;
    }

    public void open(){
        flag=true;
    }

    public void close(){
        flag=false;
    }
    @Override
    public void run(){
        //分支任务
        while(flag){
            BufferedImage image=webcam.getImage();

            g.drawImage(image,100,50,null);
        }

    }
}

