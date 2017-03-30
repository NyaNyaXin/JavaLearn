package com.cx.thread;

class SubThread3 extends Thread {

	public void run() {
		for (int i = 1; i <= 100; i++) {
			if (i % 2 == 0) {
				System.out.println(Thread.currentThread().getName() + ":" + i);
			}
		}
	}
}

class SubThread2 extends Thread {

	public void run() {
		for (int i = 1; i <= 100; i++) {
			if (i % 2 != 0) {
				System.out.println(Thread.currentThread().getName() + ":" + i);
			}
		}
	}
}

public class TestThread2 {
	public static void main(String[] args) {
		SubThread3 su1 = new SubThread3();
		//su1.start();
		SubThread2 su2 = new SubThread2();
		//su2.start();

		// 继承于Thread类的匿名类对象
		new Thread() {
			public void run() {
				for (int i = 1; i <= 100; i++) {
					if (i % 2 == 0) {
						System.out.println(Thread.currentThread().getName() + ":" + i);
					}
				}
			}
		}.start();

		new Thread() {
			public void run() {
				for (int i = 1; i <= 100; i++) {
					if (i % 2 != 0) {
						System.out.println(Thread.currentThread().getName() + ":" + i);
					}
				}
			}
		}.start();
	}
}
