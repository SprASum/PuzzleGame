package com.sprasum.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyJFrame extends JFrame implements ActionListener {
    JButton jtb1 = new JButton("按钮1");
    JButton jtb2 = new JButton("按钮2");

    public MyJFrame() {
        // 设置界面宽高
        this.setSize(603, 680);
        // 设置界面标题
        this.setTitle("事件");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置界面关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 取消默认居中放置
        this.setLayout(null);

        //按钮
        jtb1.setBounds(0, 0, 100, 50);
        jtb1.addActionListener(this);

        jtb2.setBounds(100, 0, 100, 50);
        jtb2.addActionListener(this);

        this.getContentPane().add(jtb1);
        this.getContentPane().add(jtb2);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (jtb1 == source) {
            jtb1.setSize(200, 200);
        } else if (jtb2 == source) {
            Random r = new Random();
            jtb2.setLocation(r.nextInt(500), r.nextInt(500));
        }
    }
}
