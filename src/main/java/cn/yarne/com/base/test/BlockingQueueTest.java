package cn.yarne.com.base.test;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueTest {

	/**
	 * ArrayBlockingQueue是一个有界队列 需要了解的是put,add,offer的区别:
	 * 
	 * put 如果加不进去会阻塞，直到加进去为止 add 如果加不进去会抛出错误 offer 如果加不进去会返回false
	 * 
	 * @throws InterruptedException
	 */
	private static void ArrayBlockingQueueTest() throws InterruptedException {
		ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
		arrayBlockingQueue.add("a");
		arrayBlockingQueue.add("b");
		arrayBlockingQueue.add("c");
		arrayBlockingQueue.add("d");
		arrayBlockingQueue.add("e");
		new Thread(() -> {
			try {
				Thread.sleep(3000);
				String take = arrayBlockingQueue.take();
				System.out.println(take);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		arrayBlockingQueue.put("f");

		for (Iterator iterator = arrayBlockingQueue.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
	}

	/**
	 * LinkedBlockingQueueTest是一个无界的阻塞队列，但是也可以给界限
	 */
	public static void LinkedBlockingQueueTest() {
		LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
		linkedBlockingQueue.add("a");
		linkedBlockingQueue.add("b");
		linkedBlockingQueue.add("c");
		boolean offer = linkedBlockingQueue.offer("d");
		System.out.println(offer);
		linkedBlockingQueue.offer("e");
	}
	/**
	 * SynchronousQueue是一个没有缓冲的队列，这个队列生产者生产的数据会直接被消费者进行消费
	 * 也就是说，如果没有消费者(take)方法，那么这个生产者是不能生产(put)数据的,会一直阻塞
	 */
	public static void SynchronousQueueTest() throws InterruptedException {
		SynchronousQueue<String> synchronousQueue=new SynchronousQueue<>();
		System.out.println(synchronousQueue.size());
		new Thread(()->{
			try {
				Thread.sleep(3000);
				String take = synchronousQueue.take();
				System.out.println(take+"取出");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}).start();
		new Thread(()->{
			try {
				synchronousQueue.put("添加");
				System.out.println(synchronousQueue.size());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}).start();
	}

	public static void main(String[] args) throws InterruptedException {
		
	}

}
