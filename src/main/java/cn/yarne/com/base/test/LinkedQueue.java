package cn.yarne.com.base.test;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用链表实现一个队列
 *
 * @param <E>
 */
public class LinkedQueue<E> {


    /**
     * Lock held by take, poll, etc
     */
    private final ReentrantLock takeLock = new ReentrantLock();

    /**
     * Lock held by put, offer, etc
     */
    private final ReentrantLock putLock = new ReentrantLock();


    //第一个节点
    private volatile Node<E> first;
    //最后一个节点
    private volatile Node<E> last;

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

    //链表当前长度
    private volatile AtomicInteger length = new AtomicInteger(0);
    //最大长度
    private int maxlength;

    public LinkedQueue(int capatity) {
        this.maxlength = capatity;
    }

    public void pushLast(E element) {
        if (length.get() < maxlength) {
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
            length.incrementAndGet();
        } else {
            System.out.println("太长了");
        }
        System.out.println(Thread.currentThread() + "添加数据:" + element);
    }

    public void push(E element) {
        putLock.lock();
        try {
            pushLast(element);
        } finally {
            putLock.unlock();
        }
    }

    public E dequeue() {
        takeLock.lock();
        E pop;
        try {
            pop = pop();
        } finally {
            takeLock.unlock();
        }
        return pop;
    }

    public E pop() {
        if (length.get() > 0) {
            Node<E> element = first;
            Node<E> second = first!=null?first.next:null;
            if (second != null) {
                second.prev = null;
            }
            first = second;
            length.decrementAndGet();
            return element!=null?element.item:null;
        }
        return null;

    }

}

