package com.sprasum.test;

import java.util.Random;

public class test {
    public static void main(String[] args) {
        // 打乱
        // 定义一个0-15的一维数组
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        // 打乱数组中数据的顺序
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        for (int i = 0; i < tempArr.length; i++) {
            System.out.print(tempArr[i] + "  ");
        }
        System.out.println();

        // 定义一个二维数组
        int[][] data = new int[4][4];
        // 给二维数组添加数据
        // 解法一:
        // 遍历一维数组tempArr得到每一个元素，把每一个元素依次添加到二维数组中
        /*
         *for (int i = 0; i < tempArr.length; i++) {
         *    data[i / 4][i % 4] = tempArr[i];
         *}
         */
        // 解法二:
        // 自己的想法
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = tempArr[index];
                index++;
            }
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
