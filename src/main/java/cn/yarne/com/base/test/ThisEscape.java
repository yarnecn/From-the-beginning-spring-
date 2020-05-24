package cn.yarne.com.base.test;

/**
 * @program: Eerythings
 * @description:
 * @author: yarne
 * @create: 2020-05-23 17:07
 **/
public class ThisEscape {
    final int i;
    int j = 0;

    static ThisEscape obj;

    public ThisEscape() {
        i = 1;
        j = 1;
        //将this逃逸抛出给线程B
        obj = new ThisEscape();
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(()->{
                //构造初始化中...线程B可能获取到还未被初始化完成的变量
                //类似于this逃逸，但并不定发生
                obj = new ThisEscape();
        });

        Thread threadB= new Thread(() -> {
            ThisEscape objB = obj;
            try {
                System.out.println(objB.j);
            } catch (NullPointerException e) {
                System.out.println("发生空指针错误：普通变量j未被初始化");
            }
            try {
                System.out.println(objB.i);
            } catch (NullPointerException e) {
                System.out.println("发生空指针错误：final变量i未被初始化");
            }
        });
        threadA.start();
        threadB.start();
    }
}