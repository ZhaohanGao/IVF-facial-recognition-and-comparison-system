package face;

import Match.FaceMatch;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Breon
 * @description: TODO
 * @date: 2022/12/16 16:50
 */
public class FaceVerificationPanel extends JPanel implements ActionListener {

    private Graphics g = null;
    Webcam webcam = null;
    boolean flag = false;
    RunVideoThread rvt = null;
    int score=-1;
    int x = 170;

    @Override
    protected void paintComponent(Graphics g){
        //设置组件间距
        setLayout (null);
        super.paintComponent(g);
        g.setColor(Color.black);
        g.setFont(new Font("微软雅黑",Font.BOLD,40));

        String res=(score>=80)?"匹配":"不匹配";
        g.drawString("分数: "+(score==-1? "": score),520,80);
        g.drawString("结果: "+(score==-1? "": res),520,130);

        setG(getGraphics());
        String[] strs={"启动","关闭","比对"};


        for (String str:strs){
            if(x>570) break;
            JButton btn=new JButton(str);
            //设置凸起来的按钮
            btn.setBorder(BorderFactory.createRaisedBevelBorder());
            btn.addActionListener(this);
            btn.setBounds(x, 550, 160, 60);
            x+=200;
            add(btn);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e){
        String str = e.getActionCommand();
        if (str.equals("启动")) {
            flag = true;
            webcam = Webcam.getDefault();

            webcam.setViewSize(new Dimension(320, 240));
            webcam.open();

            rvt = new RunVideoThread(webcam, g);
            rvt.open();

            Thread t = new Thread(rvt);
            t.start();

        } else if (str.equals("关闭")) {
            score=-1;
            webcam.close();
            rvt.close();
        } else if (str.equals("比对")) {
            //String fileName="D://"+System.currentTimeMillis();
            String fileName = "D://项目/facial2.0/statics/images/camera";
            WebcamUtils.capture(webcam, fileName, ImageUtils.FORMAT_PNG);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    score= FaceMatch.faceMatch().intValue();
                    if(score>=80){
                        JOptionPane.showMessageDialog(null, "比对成功","Success", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null, "比对失败","Fail", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        }
        repaint();
    }

    public void setG(Graphics g){
        this.g=g;
    }


}
