package com.cx.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 一、用于解决多线程安全问题的方式
 * synchronized:隐式锁
 * 
 * 1.同步代码块
 * 2.同步方法
 * 
 * jdk1.5以后：
 * 3.同步锁Lock
 * 注意：是一个显示锁，需要通过lock()方法上锁,必须通过unlock()方法进行释放锁
 * */
public class TestLock {
	public static void main(String[] args) {
		Ticket tk = new Ticket();
		new Thread(tk, "一号窗口").start();
		new Thread(tk, "二号窗口").start();
		new Thread(tk, "三号窗口").start();
	}
}

class Ticket implements Runnable {
	private int tick = 100;
	private Lock lock = new ReentrantLock();

	@Override
	public void run() {
		while (true) {
			lock.lock();//上锁
			try {
				if (tick > 0) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + --tick);
				}

			} finally {
				lock.unlock();//释放锁
			}

		}
	}

}
