package cn.yarne.com.base.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class StringTest {
	public static class Singltion {
		private static Singltion singltion = new Singltion();

		public static Singltion getInstance() {
			return Singltion.singltion;
		}
	}

	public static void main(String[] args) {
		new Thread(() -> {
			System.out.println(Singltion.getInstance().hashCode());
		}, "t1").start();
		new Thread(() -> {
			System.out.println(Singltion.getInstance().hashCode());
		}, "t2").start();
		new Thread(() -> {
			System.out.println(Singltion.getInstance().hashCode());
		}, "t3").start();

	}
}
