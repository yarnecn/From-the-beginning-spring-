package cn.yarne.com.base.test;

import java.util.Arrays;

/**
 * @program: Eerythings
 * @description: 三种排序方式
 * @author: yarne
 * @create: 2020-05-24 20:04
 **/
public class Sort {
    public static void main(String[] args) {
        int[] temp = {2, 4, 3, 6, 8, 5, 7};
        int[] maopao = select(temp);
        for (int i = 0; i < maopao.length; i++) {
            System.out.println(maopao[i]);
        }
    }
    /**
     * 冒泡排序
     * @param target
     * @return
     */
    public static int[] maopao(int[] target) {
        for (int i = 0; i < target.length; i++) {
            for (int j = 0; j < target.length - 1; j++) {
                if (target[j] < target[j + 1]) {
                    int temp = target[j];
                    target[j] = target[j + 1];
                    target[j + 1] = temp;
                }
            }
        }
        return target;
    }
    /**
     * 插入排序
     * @param target
     * @return
     */
    public static int[] charu(int[] target) {
        for (int i = 0; i < target.length; i++) {
            int value = target[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (target[j] > value) {
                    target[j + 1] = target[j];
                } else {
                    break;
                }
            }
            target[j + 1] = value;
        }
        return target;
    }

    /**
     * 选择排序
     * @param target
     * @return
     */
    public static int[] select(int[] target) {
        for (int i = 0; i < target.length; i++) {
            int min = i;
            for (int j = i + 1; j < target.length; j++) {
                if (target[j] < target[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int temp = target[i];
                target[i] = target[min];
                target[min] = temp;
            }
        }
        return target;
    }
}
