package cn.yarne.com.base.test;

/**
 * @program: Eerythings
 * @description: 用数组实现一个循环队列
 * @author: yarne
 * @create: 2020-05-22 22:39
 **/
public class CircleQueue {

    private String[] item;

    private int head;
    private int tail;

    private int length;

    public CircleQueue(int capatity) {
        item = new String[capatity];
        this.length = capatity;
    }

    public void push(String element) {
        int index=tail;
        if ((tail + 1) % length == head) {
            System.out.println("队列满了");
        } else {
            if(index==tail){
                item[tail] = element;
                tail=(tail+1)%length;
                System.out.println("插入了"+element);
            }
        }
    }

    public String pop() {
        int index=head;
        if (head != tail) {
            if(index==head){
                String res = item[head];
                head=(head+1)%length;
                System.out.println("消费了"+res);
                return res;
            }
            return null;
        } else {
            System.out.println("队列空了");
            return null;
        }
    }
}
