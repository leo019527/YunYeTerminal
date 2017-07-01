package com.yunye.design.usb;


import com.yunye.code.InformationBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by 李凌耀 on 2017/6/2.
 */
public class USBDesign2 extends JFrame{

    String name;

    @Override
    public String getName() {
        return name;
    }

    public USBDesign2() throws HeadlessException {
        this.name = "USBDesign2";
        this.getContentPane().setLayout(null);
        //<editor-fold desc="插入背景图">
        //插入背景图
        ImageIcon usb_bac = new ImageIcon("pic/usb/usb_bg2.jpg");
        usb_bac.setImage(usb_bac.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_DEFAULT));
        JLabel background_lable = new JLabel();
        background_lable.setIcon(usb_bac);
        background_lable.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height);
        this.add(background_lable);
        //</editor-fold>

        //<editor-fold desc="返回按钮">
        ImageIcon backButtonImg = new ImageIcon("pic/usb/usb_bac.jpg");
        backButtonImg.setImage(backButtonImg.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.092),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.044), Image.SCALE_DEFAULT));
        JButton backButton = new JButton();
        backButton.setIcon(backButtonImg);
        backButton.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.888),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.025),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.092),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.044));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(new backButtonActionListener(this));
        background_lable.add(backButton);
        //</editor-fold>

        //<editor-fold desc="选择文件界面 下一项 ">
        ImageIcon  usb_choose_next = new ImageIcon("pic/usb/usb_choose_next.jpg");
        usb_choose_next.setImage(usb_choose_next.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.151),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.072),Image.SCALE_DEFAULT));
        JButton usb_choose_nextButton = new JButton();
        usb_choose_nextButton.setIcon(usb_choose_next);
        usb_choose_nextButton.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.664),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.572),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.151),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.072));
        usb_choose_nextButton.setBorderPainted(false);
        usb_choose_nextButton.setContentAreaFilled(false);
        usb_choose_nextButton.addActionListener(new usb_choose_nextButton2ActionListener(this));
        background_lable.add(usb_choose_nextButton);
        //</editor-fold>

//        //<editor-fold desc="选择文件界面 退出">
//        ImageIcon usb_choose_exit = new ImageIcon("pic/usb/usb_choose_exit.jpg");
//        usb_choose_exit.setImage(usb_choose_exit.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.151),
//                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.072), Image.SCALE_DEFAULT));
//        JButton usb_choose_exitButton = new JButton();
//        usb_choose_exitButton.setIcon(usb_choose_exit);
//        usb_choose_exitButton.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.664),
//                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.711),
//                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.151),
//                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.072));
//        usb_choose_exitButton.addActionListener(new usb_choose_exitButtonActionListener(this));
//        background_lable.add(usb_choose_exitButton);
//        //</editor-fold>



        //<editor-fold desc="文件浏览">
        InformationBean informationBean = InformationBean.getInformationBean();
        System.out.println("D://out 下有："+ informationBean.getPages() + "个文件");
        informationBean.setOutNum(informationBean.getPages()-1);
        informationBean.setCurrentNum(0);
        ImageIcon fileScan = new ImageIcon("D:\\out\\0.jpg");
        fileScan.setImage(fileScan.getImage().getScaledInstance(401,569,Image.SCALE_DEFAULT));
        JLabel fileListed = new JLabel();
        fileListed.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.165),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.137),
                401,569);
        fileListed.setIcon(fileScan);
        background_lable.add(fileListed);
        System.out.println("现在访问的是：第"+1+"页");
        //</editor-fold>

        //<editor-fold desc="下一页">
        ImageIcon usb_lc_scan_next = new ImageIcon("pic/usb/usb_lc_scan_next.png");
        usb_lc_scan_next.setImage(usb_lc_scan_next.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.151),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.072), Image.SCALE_DEFAULT));
        JButton usb_lc_scan_nextButton = new JButton();
        usb_lc_scan_nextButton.setIcon(usb_lc_scan_next);
        usb_lc_scan_nextButton.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.664),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.433),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.151),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.072));
        usb_lc_scan_nextButton.setBorderPainted(false);
        usb_lc_scan_nextButton.setContentAreaFilled(false);
        usb_lc_scan_nextButton.addActionListener(new usb_lc_scan_nextButtonActionListener(fileListed));
        background_lable.add(usb_lc_scan_nextButton);
        //</editor-fold>

        //<editor-fold desc="上一页">
        ImageIcon usb_lc_scan_front = new ImageIcon("pic/usb/usb_lc_scan_front.png");
        usb_lc_scan_front.setImage(usb_lc_scan_front.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.151),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.072), Image.SCALE_DEFAULT));
        JButton usb_lc_scan_frontButton = new JButton();
        usb_lc_scan_frontButton.setIcon(usb_lc_scan_front);
        usb_lc_scan_frontButton.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.664),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.294),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.151),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.072));
        usb_lc_scan_frontButton.setBorderPainted(false);
        usb_lc_scan_frontButton.setContentAreaFilled(false);
        usb_lc_scan_frontButton.addActionListener(new usb_lc_scan_frontButtonActionListener(fileListed));
        background_lable.add(usb_lc_scan_frontButton);
        //</editor-fold>

        //<editor-fold desc="设置窗口大小并全屏显示">
        //设置窗口基本大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height));
        //以下两句完成了全屏显示
        this.setUndecorated(true);
        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        this.setVisible(true);
        //</editor-fold>
    }

    public static void main(String[] args) {
        new USBDesign2();
    }
}

class usb_lc_scan_nextButtonActionListener implements ActionListener
{
    private JLabel fileListed;

    public usb_lc_scan_nextButtonActionListener(JLabel fileListed) {
        this.fileListed = fileListed;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InformationBean informationBean = InformationBean.getInformationBean();
        int cur = informationBean.getCurrentNum();
        if(cur < informationBean.getOutNum())
        {
            informationBean.setCurrentNum(cur+1);
            ImageIcon fileScan = new ImageIcon("D:\\out\\"+cur+".jpg");
            fileScan.setImage(fileScan.getImage().getScaledInstance(401,569,Image.SCALE_DEFAULT));
            fileListed.setIcon(fileScan);
            fileListed.repaint();
            fileListed.setVisible(true);
        }
        else
        {
            return;
        }
    }
}

class usb_lc_scan_frontButtonActionListener implements ActionListener
{
    private JLabel fileListed;

    public usb_lc_scan_frontButtonActionListener(JLabel fileListed) {
        this.fileListed = fileListed;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InformationBean informationBean = InformationBean.getInformationBean();
        int cur = informationBean.getCurrentNum();
        if(cur > 0)
        {
            informationBean.setCurrentNum(cur-1);
            ImageIcon fileScan = new ImageIcon("D:\\out\\"+(cur-1)+".jpg");
            fileScan.setImage(fileScan.getImage().getScaledInstance(401,569,Image.SCALE_DEFAULT));
            fileListed.setIcon(fileScan);
            fileListed.repaint();
            fileListed.setVisible(true);
        }
        else
        {
            return;
        }
    }
}

class usb_choose_nextButton2ActionListener implements ActionListener
{
    JFrame Design2;

    public usb_choose_nextButton2ActionListener(JFrame design2) {
        Design2 = design2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new USBDesign3();
        Design2.dispose();
    }
}