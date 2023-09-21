package com.sprasum.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    // 创建选项下的条目
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登入");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");
    // 创建三个更换图片的条目
    JMenuItem grilItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem motionItem = new JMenuItem("运动");

    // 定义一个二维数组(用于管理数据)
    int[][] data = new int[4][4];

    // 记录空白方块在二维数组中的位置
    int x = 0;
    int y = 0;

    // 记录图片位置
    String path = "Puzzle-Game\\image\\animal\\animal3\\";

    // 定义二维数组，存储正确数据
    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16},
    };

    // 记录步数
    int step = 0;

    // 创建一个随机数对象
    Random r = new Random();

    public GameJFrame() {
        // 初始化界面
        initJFrame();

        // 初始化菜单
        initJMenuBar();

        // 初始化数据
        initData();

        //初始化图片
        initImage();

        // 设置界面是否可见
        this.setVisible(true);

    }

    // 初始化数据
    private void initData() {
        // 定义一个0-15的一维数组
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        // 打乱数组中数据的顺序
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        // 给二维数组添加数据
        // 解法一:
        // 遍历一维数组tempArr得到每一个元素，把每一个元素依次添加到二维数组中
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];
        }
    }

    // 初始化图片
    private void initImage() {
        // 清空界面中的所有组件
        this.getContentPane().removeAll();

        if (victory()) {
            // 显示胜利图标
            JLabel winJLabel = new JLabel(new ImageIcon("Puzzle-Game\\image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        // 步数统计显示
        JLabel stepJLabel = new JLabel("步数：" + step);
        stepJLabel.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepJLabel);

        // 外循环 --- 把内循环重复执行4次
        for (int i = 0; i < 4; i++) {
            // 内循环 --- 表示在一行要添加4张图片
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                // 创建一个图片的ImageIcon对象
                //ImageIcon icon = new ImageIcon("E:\\Java\\Day17\\Puzzle-Game\\image\\animal\\animal3\\1.jpg");
                // 创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon(path + num + ".jpg"));
                // 指定图片位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                // 给图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                // 把管理容器添加到界面中
                //this.add(jLabel);
                this.getContentPane().add(jLabel);
            }
        }

        // 添加背景图片
        JLabel background = new JLabel(new ImageIcon("Puzzle-Game\\image\\background.png"));
        // 设置背景图片的位置
        background.setBounds(40, 40, 508, 560);
        // 把背景图片添加到界面中
        this.getContentPane().add(background);

        // 重新绘制界面
        this.getContentPane().repaint();

    }

    // 初始化游戏窗口
    public void initJFrame() {
        // 设置界面宽高
        this.setSize(603, 680);
        // 设置界面标题
        this.setTitle("拼图单机版 v1.0");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置界面关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 取消默认居中放置
        this.setLayout(null);
        // 键盘监听事件
        this.addKeyListener(this);
    }

    // 初始化菜单
    public void initJMenuBar() {
        // 初始化菜单
        JMenuBar jMenuBar = new JMenuBar();

        // 创建菜单两个选项
        JMenu functionJMenu = new JMenu("功能");
        // 创建更换图片的选项
        JMenu changeImage = new JMenu("更换图片");
        JMenu aboutJMenu = new JMenu("关于我们");

        // 将条目添加到选项中
        functionJMenu.add(changeImage); // 将更换图片添加到功能选项中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        // 将更换图片的条目添加到更换图片选项中
        changeImage.add(grilItem);
        changeImage.add(animalItem);
        changeImage.add(motionItem);

        aboutJMenu.add(accountItem);

        // 给条目添加监听事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        grilItem.addActionListener(this);
        animalItem.addActionListener(this);
        motionItem.addActionListener(this);

        // 将选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        // 将菜单添加到界面中
        this.setJMenuBar(jMenuBar);
    }

    // 未使用
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            // 把界面中所有图片删除
            this.getContentPane().removeAll();
            // 加载完整图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            // 加载背景图片
            // 添加背景图片
            JLabel background = new JLabel(new ImageIcon("Puzzle-Game\\image\\background.png"));
            // 设置背景图片的位置
            background.setBounds(40, 40, 508, 560);
            // 把背景图片添加到界面中
            this.getContentPane().add(background);
            // 重新绘制界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 判断游戏是否结束
        if (victory()) {
            return;
        }
        // 对键盘上、下、左、右进行判断
        // 左：37 上：38 右：39 下：40
        int code = e.getKeyCode();
        switch (code) {
            case 37:
                // System.out.println("向左移动");
                if (y == 3) {
                    return;
                }
                data[x][y] = data[x][y + 1];
                data[x][y + 1] = 0;
                y++;
                step++;
                initImage();
                break;
            case 38:
                // System.out.println("向上移动");
                if (x == 3) {
                    return;
                }
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                x++;
                step++;
                initImage();
                break;
            case 39:
                // System.out.println("向右移动");
                if (y == 0) {
                    return;
                }
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                y--;
                step++;
                initImage();
                break;
            case 40:
                // System.out.println("向下移动");
                if (x == 0) {
                    return;
                }
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                x--;
                step++;
                initImage();
                break;
            case 65:
                initImage();
                break;
            case 87:
                data = new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 16},
                };
                for (int i = 0; i < data.length; i++) {
                    x = i;
                    y = i;
                }
                initImage();
                break;
            default:
                break;
        }
    }

    // 判断胜利方法
    public boolean victory() {
        // 判断data数据是否和win数据一致 一样：true 不一样：false
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // ActionListener接口中的方法
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == replayItem) {
            // 重新开始游戏
            step = 0;
            initData();
            initImage();
        } else if (source == reLoginItem) {
            // 关闭游戏界面
            this.setVisible(false);
            // 返回登入界面
            new LoginJFrame();
        } else if (source == closeItem) {
            System.exit(0);
        } else if (source == accountItem) {
            // 创建一个JDialog对象
            JDialog jDialog = new JDialog();
            // 创建一个图片的容器
            JLabel jLabel = new JLabel(new ImageIcon("Puzzle-Game\\image\\about.png"));
            // 设置位置和宽高
            jLabel.setBounds(0, 0, 258, 258);
            // 把图片容器添加到JDialog中
            jDialog.getContentPane().add(jLabel);
            // 设置JDialog的宽高
            jDialog.setSize(344, 344);
            // 设置JDialog的置顶
            jDialog.setAlwaysOnTop(true);
            // 设置JDialog的居中
            jDialog.setLocationRelativeTo(null);
            // 弹框不关闭无法操作下层界面
            jDialog.setModal(true);
            // 设置JDialog的可见性
            jDialog.setVisible(true);
        } else if (source == grilItem) {
            // 声明一个1-13的随机数
            int num = r.nextInt(13) + 1;
            // 更换图片路径
            path = "Puzzle-Game\\image\\girl\\girl" + num + "\\";
            // 重新开始游戏
            step = 0;
            initData();
            initImage();
            //System.out.println("美女");
        } else if (source == animalItem) {
            // 声明一个1-8的随机数
            int num = r.nextInt(8) + 1;
            // 更换图片路径
            path = "Puzzle-Game\\image\\animal\\animal" + num + "\\";
            // 重新开始游戏
            step = 0;
            initData();
            initImage();
            //System.out.println("动物");
        } else if (source == motionItem) {
            // 声明一个1-8的随机数
            int num = r.nextInt(10) + 1;
            // 更换图片路径
            path = "Puzzle-Game\\image\\sport\\sport" + num + "\\";
            // 重新开始游戏
            step = 0;
            initData();
            initImage();
            //System.out.println("运动");
        }
    }
}
