package cn.yarne.com.base.test;

import java.util.Objects;

/**
 * Created by 14641 on 2020/5/17.
 */
public class LinkedListDemo<E> {
    public LinkedListDemo(E i) {
    }

    private static class Node<E> {
        E item;
        LinkedListDemo.Node<E> next;
        LinkedListDemo.Node<E> prev;

        Node(LinkedListDemo.Node<E> prev, E element, LinkedListDemo.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<E> first;
    private Node<E> last;
    transient int size = 0;
    int maxSize = 5;


    void setSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public void addFirst(E element) {
        if (first == null) {
            Node<E> node = new Node<E>(null, element, null);
            this.first = node;
            this.last = node;
            ++size;
        } else {
            Node<E> node = first;
            Node<E> nowNode = new Node<E>(null, element, node);

            int contains = contains(element);
            if (contains != -1) {
                Node<E> Temp = first;
                for (int i = 0; i < contains; i++) {
                    Temp = Temp.next;
                }
                Temp.prev.next=Temp.next;
                Temp.next.prev=Temp.prev;
                nowNode = Temp;
            }
            nowNode.prev=null;
            nowNode.next=node;
            first = nowNode;
            node.prev = first;
            if (size + 1 > maxSize) {
                if(contains==-1){
                    Node<E> endTemp = last.prev;
                    endTemp.next = null;
                    last = endTemp;
                }
            } else {
                size++;
            }

        }
    }

    public int contains(E element) {
        int index = 0;
        for (Node<E> node = first; node != null; node = node.next) {
            if (Objects.equals(element, node.item)) {
                return index;
            }
            ++index;
        }
        return -1;
    }

    public void addLast(E element) {
        if (first == null) {
            Node<E> node = new Node<E>(null, element, null);
            this.first = node;
            this.last = node;
        } else {
            Node<E> node = new Node<E>(last, element, null);
            last.next = node;
            this.last = node;
        }
        size++;
    }

    LinkedListDemo.Node<E> node(int index) {
        if (size <= index) {
            throw new ArrayIndexOutOfBoundsException("数组太长了");
        }
        LinkedListDemo.Node<E> each = first;
        E target;
        for (int i = 0; i < index; i++) {
            each = each.next;
        }
        return each;
    }

    public Object[] getAll() {
        Object[] result = new Object[size];
        int i = 0;
        for (LinkedListDemo.Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    public E getFirst() {
        return first.item;
    }

    public E getLast() {
        return last.item;
    }

}
