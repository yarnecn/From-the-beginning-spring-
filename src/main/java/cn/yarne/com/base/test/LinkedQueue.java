package cn.yarne.com.base.test;


// TODO: 2020/5/21 如何实现一个支持并发Pop的队列
public class LinkedQueue<E> {

    //第一个节点
    private Node<E> first;
    //最后一个节点
    private Node<E> last;

    private static class Node<E> {
        LinkedQueue.Node<E> next;
        LinkedQueue.Node<E> prev;
        E item;

        public Node(LinkedQueue.Node<E> prev, E item, LinkedQueue.Node<E> next) {
            this.next = next;
            this.prev = prev;
            this.item = item;
        }
    }

    private Object object = new Object();
    //链表当前长度
    private int length;
    //最大长度
    private int maxlength;

    public LinkedQueue(int capatity) {
        this.maxlength = capatity;
    }

    public void pushLast(E element) {
        if (length < maxlength) {
            if (first == null) {
                Node<E> node = new Node<E>(null, element, null);
                first = node;
                last = node;
            } else {
                Node tempNode = last;
                Node nowNode = new Node(tempNode, element, null);
                last.next = nowNode;
                last = nowNode;
            }
            ++length;

        } else {
            System.out.println("太长了");
        }
        System.out.println(Thread.currentThread() + "添加数据:" + element);
    }

    public void enqueue(E element) {
        pushLast(element);
    }

    public void dequeue() {

    }

    public E pop() {
        if (length > 0) {
            Node<E> element = first;
            if (first.next != null) {
                Node<E> second = first.next;
                second.prev = null;
                first = second;
            }
            --length;
            System.out.println(Thread.currentThread() + "取出数据:" + element.item);
            return element.item;
        }
        return null;

    }

}

