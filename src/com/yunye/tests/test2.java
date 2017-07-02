package com.yunye.tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 李凌耀 on 2017/7/1.
 */
public class test2 extends JFrame {
    public test2() throws HeadlessException {
        this.setBackground(Color.blue);

        JFrame jFrame = new JFrame();
        jFrame.getContentPane().setBackground(Color.RED);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500,500);
        jFrame.setUndecorated(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        JButton jButton = new JButton("test");
        jButton.addActionListener(new A(jFrame));
        this.add(jButton);
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height));
        this.setUndecorated(true);
//        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new test2();
    }
}

class A implements ActionListener
{
    JFrame jFrame;

    public A(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jFrame.setVisible(true);
    }
}