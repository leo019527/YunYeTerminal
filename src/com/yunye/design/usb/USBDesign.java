package com.yunye.design.usb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.alipay.demo.trade.Main;
import com.yunye.code.InformationBean;
import com.yunye.code.USB.*;
import com.yunye.code.analyzeAndPrint.OfficeAnalyze;
import com.yunye.code.analyzeAndPrint.Price;
import org.apache.poi.ss.formula.functions.T;

/**
 * Created by 李凌耀 on 2017/4/7.
 */
public class USBDesign extends JFrame {
    File USB = null;
    String name;

    @Override
    public String getName() {
        return name;
    }

    public USBDesign(File USB) throws HeadlessException {
        this.name = "USBDesign1";
        this.USB = USB;

        this.getContentPane().setLayout(null);

        //<editor-fold desc="插入背景图">
        //插入背景图
        ImageIcon usb_bac = new ImageIcon("pic/usb/usb_bg1.jpg");
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




        //<editor-fold desc="打开选择文件界面">
        //<editor-fold desc="选择文件界面 直接打印">
        ImageIcon usb_choose_print = new ImageIcon("pic/usb/usb_choose_print.jpg");
        usb_choose_print.setImage(usb_choose_print.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.151),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.072), Image.SCALE_DEFAULT));
        JButton usb_choose_printButton = new JButton();
        usb_choose_printButton.setIcon(usb_choose_print);
        usb_choose_printButton.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.764),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.334),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.151),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.072));
        usb_choose_printButton.setBorderPainted(false);
        usb_choose_printButton.setContentAreaFilled(false);
        usb_choose_printButton.addActionListener(new usb_choose_printButtonActionlistener(background_lable));
        background_lable.add(usb_choose_printButton);
        //</editor-fold>

        //<editor-fold desc="选择文件界面 下一项 ">
        ImageIcon  usb_choose_next = new ImageIcon("pic/usb/usb_choose_next.jpg");
        usb_choose_next.setImage(usb_choose_next.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.151),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.072),Image.SCALE_DEFAULT));
        JButton usb_choose_nextButton = new JButton();
        usb_choose_nextButton.setIcon(usb_choose_next);
        usb_choose_nextButton.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.764),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.472),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.151),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.072));
        usb_choose_nextButton.setBorderPainted(false);
        usb_choose_nextButton.setContentAreaFilled(false);
        usb_choose_nextButton.addActionListener(new usb_choose_nextButtonActionListener(this));
        background_lable.add(usb_choose_nextButton);
        //</editor-fold>

        //<editor-fold desc="选择文件界面 退出">
        ImageIcon usb_choose_exit = new ImageIcon("pic/usb/usb_choose_exit.jpg");
        usb_choose_exit.setImage(usb_choose_exit.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.151),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.072), Image.SCALE_DEFAULT));
        JButton usb_choose_exitButton = new JButton();
        usb_choose_exitButton.setIcon(usb_choose_exit);
        usb_choose_exitButton.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.764),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.611),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.151),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.072));
        usb_choose_exitButton.setBorderPainted(false);
        usb_choose_exitButton.setContentAreaFilled(false);
        usb_choose_exitButton.addActionListener(new usb_choose_exitButtonActionListener(this));
        background_lable.add(usb_choose_exitButton);
        //</editor-fold>

        //<editor-fold desc="文件显示界面">
//        JPanel fileBasePanel = new JPanel();
//        fileBasePanel.setBounds(0,0,(int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.425),
//                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.749));
//        fileBasePanel.setLayout(null);
        JPanel fileListed = new JPanel();
        fileListed.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.165),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.137),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.425),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.749));
        background_lable.add(fileListed);
        UI ui = new UI(fileListed, this.USB);
        fileListed.add(ui);
        //</editor-fold>
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


    //测试
    public static void main(String[] args) {
//        new USBDesign(new File("I:\\Test"));
        new USBDesign(new File("I:\\"));
    }

}


//右上角返回按钮的监听类
class backButtonActionListener implements ActionListener {
    JFrame ToClose = null;

    public backButtonActionListener(JFrame toClose) {
        ToClose = toClose;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ToClose.dispose();
        //TODO：插入u盘退出方法
    }
}

//选择文件界面 直接打印 监听
class usb_choose_printButtonActionlistener implements ActionListener {
    private File USB = null;
    private JLabel bg = null;

    public usb_choose_printButtonActionlistener(JLabel bg) {
        this.bg = bg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InformationBean bean = InformationBean.getInformationBean();
        USB = bean.getBean();
        if (!USB.isDirectory()) {
            //TODO:
            OfficeAnalyze officeAnalyze = OfficeAnalyze.getInstance();
            officeAnalyze.setFile(USB);
            int pages = officeAnalyze.getPages();
            if(pages == -1)
            {
                return;
            }
            double v = Price.getPrice();
            Main m = new Main();
            String ordername = m.pay(String.valueOf(v));
            System.out.println(ordername);

//            try {
//                m.main(new String[]{String.valueOf(v),"pay"});
//            } catch (AlipayApiException e1) {
//                e1.printStackTrace();
//            }
            USBDesign4 USBDesign4 = new USBDesign4();
//            ImageIcon qr = new ImageIcon("D://out.png");
//            JLabel qrLable = new JLabel(qr);
//            bg.add(qrLable);
//            bg.repaint();
//            bg.setVisible(true);

            //officeAnalyze.print();
        } else {
            return;
        }
    }
}

//选择文件界面 下一项 监听
class usb_choose_nextButtonActionListener implements ActionListener {
    JFrame Design;

    public usb_choose_nextButtonActionListener(JFrame design) {
        Design = design;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InformationBean bean = InformationBean.getInformationBean();
        if(bean.getBean() == null)
        {
            return;
        }
        File USB = bean.getBean();
        if (!USB.isDirectory()) {
            OfficeAnalyze officeAnalyze = OfficeAnalyze.getInstance();
            officeAnalyze.setFile(USB);
            bean.setPages(officeAnalyze.getPages());
        }
        USBDesign2 usbDesign2 = new USBDesign2();
        Design.dispose();
    }
}

//选择文件界面 退出 监听
class usb_choose_exitButtonActionListener implements ActionListener {
    JFrame toClose;

    public usb_choose_exitButtonActionListener(JFrame toClose) {
        this.toClose = toClose;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.toClose.dispose();
        //TODO：插入u盘退出方法
    }
}