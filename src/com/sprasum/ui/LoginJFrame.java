package com.sprasum.ui;

import com.sprasum.domain.User;
import com.sprasum.util.CodeUtil;
import com.sprasum.util.Dialog;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements MouseListener {
    // 用户集合
    static ArrayList<User> allUser = new ArrayList<>();

    // 手动添加用户
    static {
        allUser.add(new User("admin", "admin"));
        allUser.add(new User("root", "root"));
        allUser.add(new User("sprasum", "sprasum"));
    }

    // 路径
    String path = "Puzzle-Game\\image\\login\\";

    // 验证码
    String code = new CodeUtil().getCode();

    // 创建验证码的JLabel对象
    JLabel codeJLabel = new JLabel();

    // 登入和注册按钮的JButton对象
    JButton loginBtn = new JButton();
    JButton registerBtn = new JButton();

    // 登入、注册、验证码的文本框
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField verifyField = new JTextField();

    // 显示密码的JButton对象
    JButton showPasswordJbtn = new JButton();


    public LoginJFrame() {
        // 初始化界面
        initJFrame();

        // 初始化图片
        initView();

        // 设置界面是否可见
        this.setVisible(true);

    }

    public void initJFrame() {
        // 设置界面宽高
        this.setSize(488, 430);
        // 设置界面标题
        this.setTitle("登入拼图单机版 v1.0");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置界面关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 取消默认居中放置
        this.setLayout(null);
    }

    // 初始化组件
    public void initView() {
        // 清空所有组件
        this.getContentPane().removeAll();

        // 初始化表单
        initForm();

        // 用于显示明文密码的输入框

        // 显示密码的按钮
        // 设置按钮大小和位置
        showPasswordJbtn.setBounds(350, 200, 20, 20);
        // 设置按钮背景
        showPasswordJbtn.setIcon(new ImageIcon(path + "显示密码.png"));
        // 去除按钮默认边框
        showPasswordJbtn.setBorderPainted(false);
        // 去除按钮默认背景
        showPasswordJbtn.setContentAreaFilled(false);
        showPasswordJbtn.addMouseListener(this);
        this.getContentPane().add(showPasswordJbtn);


        // 显示需要输入的验证码
        // 设置文字
        codeJLabel.setText(code);
        // 设置位置和大小
        codeJLabel.setBounds(260, 235, 50, 50);
        // 验证码单击事件
        codeJLabel.addMouseListener(this);
        // 添加到界面
        this.getContentPane().add(codeJLabel);

        // 添加登入和注册按钮
        // 登入按钮
        // 设置按钮的位置和大小
        loginBtn.setBounds(123, 310, 128, 47);
        // 给按钮添加背景图片
        loginBtn.setIcon(new ImageIcon(path + "登录按钮.png"));
        // 去除按钮的默认边框
        loginBtn.setBorderPainted(false);
        // 去除按钮默认背景
        loginBtn.setContentAreaFilled(false);
        // 登入按钮事件
        loginBtn.addMouseListener(this);
        this.getContentPane().add(loginBtn);
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
        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setBounds(100, 150, 50, 20);
        this.getContentPane().add(usernameLabel);
        // 密码
        JLabel passwordLabel = new JLabel("密  码:");
        passwordLabel.setBounds(100, 200, 50, 20);
        this.getContentPane().add(passwordLabel);
        // 验证码
        JLabel verifyLabel = new JLabel("验证码:");
        verifyLabel.setBounds(100, 250, 50, 20);
        this.getContentPane().add(verifyLabel);

        // 表单输入框
        // 用户名
        usernameField.setBounds(150, 150, 200, 20);
        this.getContentPane().add(usernameField);
        // 密码
        passwordField.setBounds(150, 200, 200, 20);
        // 密码框默认显示*
        passwordField.setEchoChar('*');
        this.getContentPane().add(passwordField);
        // 验证码
        verifyField.setBounds(150, 250, 100, 20);
        this.getContentPane().add(verifyField);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == codeJLabel) {
            //System.out.println("重新获取验证码");
            // 重新赋值验证码
            code = new CodeUtil().getCode();
            codeJLabel.setText(code);
        } else if (source == registerBtn) {
            // 跳转到注册界面
            new RegisterJFrame();
            // 关闭当前界面
            this.dispose();
        } else if (source == loginBtn) {
            // 获取到输入的用户名和密码
            String usernameInput = usernameField.getText();
            String passwordInput = passwordField.getText();

            // 获取到验证码
            String codeInput = verifyField.getText();

            // 创建User对象
            User userinfo = new User(usernameInput, passwordInput);

            // 判断输入数据是否合法
            if (codeInput.length() == 0) {
                // 判断验证码是否为空
                new Dialog().showDialog("验证码为空");
                code = new CodeUtil().getCode();
                codeJLabel.setText(code);
            } else if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                // 判断用户名或密码是否为空
                new Dialog().showDialog("用户名或密码为空");
            } else if (!codeInput.equalsIgnoreCase(code)) {
                // 判断验证码是否正确
                new Dialog().showDialog("验证码错误");
                code = new CodeUtil().getCode();
                codeJLabel.setText(code);
            } else if (contains(userinfo)) {
                // 判断用户是否存在以及密码是否正确
                // 关闭当前界面
                this.setVisible(false);
                // 跳转到游戏界面
                new GameJFrame();
            } else {
                new Dialog().showDialog("用户名或密码错误");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
        if (source == loginBtn) {
            loginBtn.setIcon(new ImageIcon("Puzzle-Game\\image\\login\\登录按下.png"));
        } else if (source == registerBtn) {
            registerBtn.setIcon(new ImageIcon("Puzzle-Game\\image\\login\\注册按下.png"));
        } else if (source == showPasswordJbtn) {
            showPasswordJbtn.setIcon(new ImageIcon("Puzzle-Game\\image\\login\\显示密码按下.png"));
            // 显示明文密码
            passwordField.setEchoChar((char) 0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getSource();
        if (source == loginBtn) {
            loginBtn.setIcon(new ImageIcon("Puzzle-Game\\image\\login\\登录按钮.png"));
        } else if (source == registerBtn) {
            registerBtn.setIcon(new ImageIcon("Puzzle-Game\\image\\login\\注册按钮.png"));
        } else if (source == showPasswordJbtn) {
            showPasswordJbtn.setIcon(new ImageIcon("Puzzle-Game\\image\\login\\显示密码.png"));
            // 隐藏明文密码
            passwordField.setEchoChar('*');
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // 判断用户在集合中是否存在
    public boolean contains(User userInput) {
        for (int i = 0; i < allUser.size(); i++) {
            User rightUser = allUser.get(i);
            if (rightUser.getUsername().equals(userInput.getUsername()) && rightUser.getPassword().equals(userInput.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
