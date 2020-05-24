package cn.yarne.com.base.test;


/**
 * @program: Eerythings
 * @description: 斐波那契数列
 * @author: yarne
 * @create: 2020-05-21 21:37
 **/
public class Fibonacci {

    public static int digui(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return digui(n - 1) + digui(n - 2);
    }

    public static int fori(int n) {
        int a = 1;
        int b = 1;
        for (int i = 0; i < n - 1; i++) {
            int t = a + b;
            a = b;
            b = t;
        }
        return b;
    }
}
