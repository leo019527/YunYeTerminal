package com.yunye.design.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.yunye.code.USB.*;
import com.yunye.code.analyzeAndPrint.OfficeAnalyze;
import com.yunye.design.online.onlineIndex;

/**
 * Created by 李凌耀 on 2017/4/6.
 */
public class Main extends JFrame {
    JPanel imgBackground;//直接覆盖在底层上的背景图的标签
    JButton indexUSB;//USB打印按钮
    JButton indexOnline;//线上打印按钮
    JButton indexScan;//证件扫描
    String name;//窗口名字

    @Override
    public String getName() {
        return name;
    }

    //构造函数:搭建UI绑定监听器
    public Main() throws HeadlessException {
        this.name = "Main";
        this.getContentPane().setLayout(null);

        //<editor-fold desc="插入背景图">
        //插入背景图
        ImageIcon indexBG = new ImageIcon("pic/main/main.png");//背景图index_bg.jpg
        indexBG.setImage(indexBG.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height,Image.SCALE_DEFAULT));
        JLabel label = new JLabel();
        label.setIcon(indexBG);
        label.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height);
        this.add(label);
        //</editor-fold>

        //<editor-fold desc="两个个打印选项">
        //插入USB打印按钮
        ImageIcon imageIcon1 = new ImageIcon("pic/main/main_usbButtom.png");
        indexUSB = new JButton();
        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.269),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.101),Image.SCALE_DEFAULT));
        indexUSB.setIcon(imageIcon1);
        indexUSB.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.633),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.286),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.269),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.101));
        indexUSB.setBorderPainted(false);
        indexUSB.setContentAreaFilled(false);
        indexUSB.addActionListener(new USBActionListener(label));
        label.add(indexUSB);

        //插入线上打印按钮
        ImageIcon imageIcon2 = new ImageIcon("pic/main/main_onlineButtom.png");
        indexOnline = new JButton();
        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.269),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.101),Image.SCALE_DEFAULT));
        indexOnline.setIcon(imageIcon2);
        indexOnline.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.633),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.471),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.269),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.101));
        indexOnline.setBorderPainted(false);
        indexOnline.setContentAreaFilled(false);
        indexOnline.addActionListener(new OnlineActionListener());
        label.add(indexOnline);

        //<editor-fold desc="证件扫描打印，暂时不需要">
        //插入证件扫描按钮
//        ImageIcon imageIcon3 = new ImageIcon("pic/main/main_scanButtom.png");
//        indexScan = new JButton();
//        imageIcon3.setImage(imageIcon3.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.269),
//                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.101),Image.SCALE_DEFAULT));
//        indexScan.setIcon(imageIcon3);
//        indexScan.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.633),
//                (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.668),
//                (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.269),
//                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.101));
//        indexScan.addActionListener(new ScanActionListener());
//        label.add(indexScan);
        //</editor-fold>

        //</editor-fold>

        //<editor-fold desc="剩余纸张">
        //        ImageIcon icon = new ImageIcon("pic/main/main_remaining.png");
//        icon.setImage(icon.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.125),
//                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.068),Image.SCALE_DEFAULT));
        JLabel remaining = new JLabel();
//        remaining.setIcon(icon);
        Font font = new Font("宋体",Font.PLAIN,20);
        remaining.setFont(font);
        remaining.setForeground(Color.white);
        remaining.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.031),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.911),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.205),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.068));
        remaining.setText("剩余纸张：500 张");
        label.add(remaining);
        OfficeAnalyze.getInstance().setRemainingPaper(remaining);
        //</editor-fold>

        //<editor-fold desc="终端机分布按钮">
        ImageIcon icon = new ImageIcon("pic/main/main_distribution.png");
        icon.setImage(icon.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.127),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.068),Image.SCALE_DEFAULT));
        JButton distribution = new JButton();
        distribution.setIcon(icon);
        distribution.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.207),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.911),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.127),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.068));
        distribution.setBorderPainted(false);
        distribution.setContentAreaFilled(false);
        distribution.addActionListener(new distributionActionListener());
        label.add(distribution);
        //</editor-fold>

        //<editor-fold desc="点我开始云打印">
        ImageIcon icon1 = new ImageIcon("pic/main/main_startUp.png");
        icon1.setImage(icon1.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.160),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.068),Image.SCALE_DEFAULT));
        JButton startUp = new JButton();
        startUp.setIcon(icon1);
        startUp.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.662),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.911),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.160),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.068));
        startUp.setBorderPainted(false);
        startUp.setContentAreaFilled(false);
        startUp.addActionListener(new startUpActionListener());
        label.add(startUp);
        //</editor-fold>

        //<editor-fold desc="联系我们">
        ImageIcon icon2 = new ImageIcon("pic/main/main_contactus.png");
        icon2.setImage(icon2.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.100),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.068),Image.SCALE_DEFAULT));
        JButton contactus = new JButton();
        contactus.setIcon(icon2);
        contactus.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.850),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.911),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.100),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.068));
        contactus.setBorderPainted(false);
        contactus.setContentAreaFilled(false);
        contactus.addActionListener(new contactusActionListener());
        label.add(contactus);
        //</editor-fold>

        //测试时方便退出
//        JButton eeee = new JButton("测试退出");
//        eeee.setBounds(0,0,200,50);
//        eeee.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });
//        label.add(eeee);

        //<editor-fold desc="设置窗口大小并全屏显示">
        //设置窗口基本大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height));
        //以下两句完成了全屏显示
        this.setUndecorated(true);
        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        this.setVisible(true);
        //</editor-fold>
        System.out.println("生成主界面成功");

    }



    //程序入口
    public static void main(String[] args) {
        new Main();
    }
}

//以下为三个按钮绑定的监听函数
class OnlineActionListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        new onlineIndex();
    }
}

class USBActionListener implements ActionListener {

    JLabel jLabel2 = null;

    public USBActionListener(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //<editor-fold desc="显示查询图片">
        JLabel jLabel = new JLabel();
        ImageIcon indexBG = new ImageIcon("pic/main/main_usb.png");//背景图index_bg.jpg
        indexBG.setImage(indexBG.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.4),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.4), Image.SCALE_DEFAULT));
        jLabel.setIcon(indexBG);
        jLabel.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.2),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.4),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.4),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.4));
        jLabel.addMouseListener(new removeAfromBMouseListener(jLabel,jLabel2));
        jLabel2.add(jLabel);
        jLabel2.repaint();
        jLabel2.setVisible(true);
        //</editor-fold>
        System.out.println("显示查询图片");

        //<editor-fold desc="创建usb错误图片">
        JLabel jLabe3 = new JLabel();
        ImageIcon imageIcon = new ImageIcon("pic/main/main_usbError.png");//背景图index_bg.jpg
        imageIcon.setImage(imageIcon.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.4),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.4), Image.SCALE_DEFAULT));
        jLabe3.setIcon(imageIcon);
        jLabe3.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.2),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.4),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.4),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.4));
        //</editor-fold>



        //<editor-fold desc="创建USB处理类">
        Res res = new Res();
        USBMain usbMain = new USBMain(res,jLabel2,jLabel,jLabe3);
        usbMain.start();
        //</editor-fold>





    }

}

class removeAfromB extends Thread
{
    JLabel A;
    JLabel B;

    public removeAfromB(JLabel a, JLabel b) {
        A = a;
        B = b;
    }

    @Override
    public void run() {
        B.remove(A);
        B.repaint();
        B.setVisible(true);
    }
}

class removeAfromBMouseListener implements MouseListener
{
    JLabel A;
    JLabel B;

    public removeAfromBMouseListener(JLabel a, JLabel b) {
        A = a;
        B = b;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        new removeAfromB(A,B).run();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

class ScanActionListener implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO：插入证件扫描代码
    }
}

class distributionActionListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO:插入终端机分布代码
    }
}

class startUpActionListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO:插入帮助代码
    }
}

class contactusActionListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO:插入联系我们代码
    }
}