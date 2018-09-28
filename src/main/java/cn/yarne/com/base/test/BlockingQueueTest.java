package cn.yarne.com.base.test;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import cn.yarne.com.base.test.BlockingQueueTest.Comp;
import cn.yarne.com.base.test.BlockingQueueTest.DelayTest;

public class BlockingQueueTest {

	/**
	 * ArrayBlockingQueue是一个有界队列 需要了解的是put,add,offer的区别:
	 * put 如果加不进去会阻塞，直到加进去为止 add 如果加不进去会抛出错误 offer 如果加不进去会返回false
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
	 * LinkedBlockingQueueTest是一个无界的阻塞队列，但是也可以给界限，其他的和ArrayBlockingQueue相似
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
		SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
		System.out.println(synchronousQueue.size());
		new Thread(() -> {
			try {
				Thread.sleep(3000);
				String take = synchronousQueue.take();
				System.out.println(take + "取出");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}).start();
		new Thread(() -> {
			try {
				synchronousQueue.put("添加");
				System.out.println(synchronousQueue.size());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}).start();
	}

	class Comp implements Comparable<Comp> {
		private String name;
		private int id;

		public Comp(String name, int id) {
			super();
			this.name = name;
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		@Override
		public int compareTo(Comp o) {
			return this.id - o.getId();
		}

		@Override
		public String toString() {
			return "Comp [name=" + name + ", id=" + id + "]";
		}

	}

	/**
	 * PriorityBlockingQueue 是一个基于优先级的阻塞队列
	 * 说到优先级肯定是需要排序，所以说想要实现排序，传入的对象必须实现Comparable接口
	 * 这个队列的排序是在add或者take的时候，每次把最高优先级的一个放在第一位，准备被下一次take出去
	 * 
	 * 
	 * @throws InterruptedException
	 */
	public static void PriorityBlockingQueueTest() throws InterruptedException {
		BlockingQueueTest blockingQueueTest = new BlockingQueueTest();
		PriorityBlockingQueue<Comp> priorityBlockingQueue = new PriorityBlockingQueue<>();

		Comp comp2 = blockingQueueTest.new Comp("李四", 8);
		Comp comp1 = blockingQueueTest.new Comp("张三", 3);
		Comp comp3 = blockingQueueTest.new Comp("王五", 6);
		Comp comp4 = blockingQueueTest.new Comp("王五", 7);

		priorityBlockingQueue.add(comp1);
		priorityBlockingQueue.add(comp2);
		priorityBlockingQueue.add(comp3);
		priorityBlockingQueue.add(comp4);

		for (Iterator iterator = priorityBlockingQueue.iterator(); iterator.hasNext();) {
			Comp comp = (Comp) iterator.next();
			System.out.println(comp.toString());
		}

		Comp take = priorityBlockingQueue.take();
		Comp take2 = priorityBlockingQueue.take();
		System.out.println(take.toString());
		System.out.println(take2.toString());

	}

	class DelayTest implements Delayed {
		private int id;
		private long time;

		public DelayTest(int id, long time) {
			super();
			this.id = id;
			this.time = time;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public long getTime() {
			return time;
		}

		public void setTime(long time) {
			this.time = time;
		}

		@Override
		public int compareTo(Delayed o) {
			DelayTest delayTest = (DelayTest) o;
			return this.id - delayTest.id;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			long ti=this.time-System.currentTimeMillis();
			System.out.println(ti);
			return ti;
		}

		@Override
		public String toString() {
			return "DelayTest [id=" + id + ", time=" + time + "]";
		}
	}

	/**
	 * DelayQueue 是一个带有延时时间的队列，元素只有时间到了,take的时候才能取到元素
	 * 所以DelayQueue中存入的元素必须实现Delayed接口，主要判断时间是否到期的方法是
	 * getDelay() 所以这个方法中的返回值必须是一个不断减少的值，例如lthis.time-System.currentTimeMillis();
	 * 否则的话会导致take方法等不到过期的元素，所以会阻塞
	 * 这是一个没有大小限制的队列，使用场景很多，比如缓存超时处理，任务超时的处理，空闲连接的关闭
	 * @throws InterruptedException
	 */
	public static void DelayQueueTest() throws InterruptedException {
		DelayQueue<DelayTest> delayQueue = new DelayQueue<>();
		BlockingQueueTest blockingQueueTest = new BlockingQueueTest();
		DelayTest delayTest1 = blockingQueueTest.new DelayTest(3, System.currentTimeMillis()+3000);
		DelayTest delayTest2 = blockingQueueTest.new DelayTest(5, System.currentTimeMillis()+5000);
		DelayTest delayTest3 = blockingQueueTest.new DelayTest(10, System.currentTimeMillis()+10000);
		delayQueue.add(delayTest2);
		delayQueue.add(delayTest3);
		delayQueue.add(delayTest1);

		for (Iterator iterator = delayQueue.iterator(); iterator.hasNext();) {
			DelayTest delayTest = (DelayTest) iterator.next();
			System.out.println(delayTest.toString());
		}
		DelayTest take = delayQueue.take();
		System.out.println(take.toString());
		DelayTest take1 = delayQueue.take();
		System.out.println(take1.toString());
		DelayTest take2 = delayQueue.take();
		System.out.println(take2.toString());
	}
	
	public static void main(String[] args) throws InterruptedException {
		
	}

}
