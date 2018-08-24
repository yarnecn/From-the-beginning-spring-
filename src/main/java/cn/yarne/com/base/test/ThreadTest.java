package cn.yarne.com.base.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ThreadTest {
	
	   // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    //效率高，以后少用+""这种，效率很慢，要多看别人写的代码
    public static StringBuffer stringBuffer = new StringBuffer();

    public static void main(String[] args) throws Exception {
    	//新建一个执行器线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //计数信号量 用来定义请求总数
        final Semaphore semaphore = new Semaphore(threadTotal);
        //实现多个线程开始执行任务的最大并行性
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
        	//lamda表达式
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (Exception e) {
                   // log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        //log.info("size:{}", stringBuffer.length());
       System.out.println(stringBuffer.length());
    }

    private static void update() {
        stringBuffer.append("1");
    }
}
