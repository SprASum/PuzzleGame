package com.sprasum.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test2 {
    public static void main(String[] args) {
        //事件
        JFrame jFrame = new JFrame();
        jFrame.setSize(603, 600);
        jFrame.setTitle("事件演示");
        jFrame.setAlwaysOnTop(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(null);

        JButton jtb = new JButton("点我呀");
        jtb.setBounds(0, 0, 100, 50);
        // 使用自己创建的实现类
        //jtb.addActionListener(new MyActionListener());
        // 使用匿名内部类对象
        jtb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("达咩~~不要点我呀");
            }
        });

        jFrame.getContentPane().add(jtb);


        jFrame.setVisible(true);
    }
}
