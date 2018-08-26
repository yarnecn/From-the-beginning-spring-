package cn.yarne.com.base.test;

/**
 * 测试死锁
 * @author yarne
 *
 */
public class BlockThreadTest implements Runnable {

	public static Object o1 = new Object();

	public static Object o2 = new Object();

	private boolean flag;

	public BlockThreadTest(boolean flag) {

		this.flag = flag;

	}

	@Override

	public void run() {

		String threadName = Thread.currentThread().getName();

		if (flag) {

			while (true) {

				synchronized (o1) {

					try {

						Thread.sleep(1000);

					} catch (InterruptedException e) {

						e.printStackTrace();

					}

					System.out.println(threadName + "get o1 lock");

					synchronized (o2) {

						System.out.println(threadName + "get o2 lock");

					}

				}

			}

		} else {

			while (true) {

				synchronized (o2) {

					try {

						Thread.sleep(1000);

					} catch (InterruptedException e) {

						e.printStackTrace();

					}

					System.out.println(threadName + "get o2 lock");

					synchronized (o1) {

						System.out.println(threadName + "get o1 lock");

					}

				}

			}

		}

	}

	public static void main(String[] args) {

		BlockThreadTest threada = new BlockThreadTest(true);

		BlockThreadTest threadb = new BlockThreadTest(false);

		Thread threada1 = new Thread(threada);

		Thread threadb1 = new Thread(threadb);

		threada1.start();

		threadb1.start();

	}
}
