package com.sprasum.ui;

import com.sprasum.domain.User;
import com.sprasum.util.Dialog;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class RegisterJFrame extends JFrame implements MouseListener {
    // 用户集合
    ArrayList<User> allUser = new ArrayList<>();

    // 路径
    String path = "Puzzle-Game\\image\\register\\";

    // 用户名、密码、确认密码的文本框
    // 用户名
    JTextField usernameField = new JTextField();
    // 密码
    JPasswordField passwordField = new JPasswordField();
    // 确认密码
    JPasswordField confirmPasswordField = new JPasswordField();

    // 重置和注册按钮的JButton对象
    JButton resetBtn = new JButton();
    JButton registerBtn = new JButton();


    public RegisterJFrame() {

        // 初始化界面
        initJFrame();

        // 初始化组件
        initView();

        // 设置界面是否可见
        this.setVisible(true);

    }

    // 初始化界面
    public void initJFrame() {
        // 设置界面宽高
        this.setSize(488, 430);
        // 设置界面标题
        this.setTitle("注册拼图单机版 v1.0");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置界面关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // 初始化组件
    public void initView() {
        // 清空所有组件
        this.getContentPane().removeAll();

        // 初始化表单
        initForm();

        // 添加重置和注册按钮
        // 重置按钮
        // 设置按钮的位置和大小
        resetBtn.setBounds(123, 310, 128, 47);
        // 给按钮添加背景图片
        resetBtn.setIcon(new ImageIcon(path + "重置按钮.png"));
        // 去除按钮的默认边框
        resetBtn.setBorderPainted(false);
        // 去除按钮默认背景
        resetBtn.setContentAreaFilled(false);
        // 登入按钮事件
        resetBtn.addMouseListener(this);
        this.getContentPane().add(resetBtn);
        // 注册按钮
        registerBtn.setBounds(256, 310, 128, 47);
        registerBtn.setIcon(new ImageIcon(path + "注册按钮.png"));
        //去除按钮的默认边框
        registerBtn.setBorderPainted(false);
        //去除按钮的默认背景
        registerBtn.setContentAreaFilled(false);
        // 注册按钮事件
        registerBtn.addMouseListener(this);
        this.getContentPane().add(registerBtn);

        // 添加背景图片
        // 创建一个图片的容器
        JLabel background = new JLabel(new ImageIcon(path + "background.png"));
        // 设置图片的位置和大小
        background.setBounds(3, 0, 470, 390);
        // 添加到界面
        this.getContentPane().add(background);
    }

    // 初始化表单
    public void initForm() {
        // 表单的文本
        // 用户名
        JLabel usernameLabel = new JLabel("用户名");
        usernameLabel.setBounds(100, 150, 50, 30);
        this.getContentPane().add(usernameLabel);
        // 密码
        JLabel passwordLabel = new JLabel("密码");
        passwordLabel.setBounds(100, 200, 50, 30);
        this.getContentPane().add(passwordLabel);
        // 确认密码
        JLabel confirmPasswordLabel = new JLabel("确认密码");
        confirmPasswordLabel.setBounds(100, 250, 50, 30);
        this.getContentPane().add(confirmPasswordLabel);

        // 用户名、密码、确认密码的文本框
        // 用户名
        usernameField.setBounds(160, 150, 200, 30);
        this.getContentPane().add(usernameField);
        // 密码
        passwordField.setBounds(160, 200, 200, 30);
        // 密码框默认显示*
        passwordField.setEchoChar('*');
        this.getContentPane().add(passwordField);
        // 确认密码
        confirmPasswordField.setBounds(160, 250, 200, 30);
        // 确认密码框默认显示*
        confirmPasswordField.setEchoChar('*');
        this.getContentPane().add(confirmPasswordField);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == resetBtn) {
            // 清空文本框
            usernameField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
        } else if (source == registerBtn) {
            // 注册事件
            // 获取用户输入内容
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            User userinfo = new User(username, password);

            if (username.length() == 0 || password.length() == 0 || confirmPassword.length() == 0) {
                // 判断用户名或密码是否为空
                new Dialog().showDialog("用户名或密码不能为空");
            } else if (contains(userinfo)) {
                // 判断用户是否存在
                new Dialog().showDialog("用户名已存在");
                // 清空文本框
                usernameField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
            } else if (!password.equals(confirmPassword)) {
                // 判断两次密码是否一致
                new Dialog().showDialog("两次密码不一致");
                // 清空文本框
                usernameField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
            } else {
                // 提示注册成功
                new Dialog().showDialog("注册成功");
                // 把数据传入到LoginJFrame的集合中并且跳转
                new LoginJFrame().allUser.add(userinfo);
                // 关闭当前界面
                this.dispose();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
        if (source == resetBtn) {
            resetBtn.setIcon(new ImageIcon("Puzzle-Game\\image\\register\\重置按下.png"));
        } else if (source == registerBtn) {
            registerBtn.setIcon(new ImageIcon("Puzzle-Game\\image\\register\\注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getSource();
        if (source == resetBtn) {
            resetBtn.setIcon(new ImageIcon("Puzzle-Game\\image\\register\\重置按钮.png"));
        } else if (source == registerBtn) {
            registerBtn.setIcon(new ImageIcon("Puzzle-Game\\image\\register\\注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // 判断用户是否存在
    public boolean contains(User userInput) {
        for (int i = 0; i < allUser.size(); i++) {
            User rightUser = allUser.get(i);
            if (rightUser.getUsername().equals(userInput.getUsername())) {
                return true;
            }
        }
        return false;
    }
}
