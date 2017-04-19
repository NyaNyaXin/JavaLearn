package com.cx.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//生产者和消费者案例
public class TestProductorAndConsumerForLock {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		Productor productor = new Productor(clerk);
		Consumer consumer = new Consumer(clerk);
		new Thread(productor, "生产者A").start();
		new Thread(consumer, "消费者B").start();
		new Thread(productor, "生产者C").start();
		new Thread(consumer, "消费者D").start();
	}
}

// 店员
class Clerk {
	private int product = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition= lock.newCondition();
	// 进货
	public void get() {// 循环次数：2
		lock.lock();
		try {
			while (product >= 1) {// 为了避免虚假唤醒问题，应该总是使用在循环中
				System.out.println("产品已满，无法添加");
				try {
					condition.await();
				} catch (InterruptedException e) {
				}
			}
			System.out.println(Thread.currentThread().getName() + ":" + ++product);
			condition.signalAll();
		} finally {
			lock.unlock();
		}

	}

	// 卖货
	public void sale() {// 循环次数：1 product = 0;
		lock.lock();
		try {
			while (product <= 0) {
				System.out.println("缺货");
				try {
					condition.await();
				} catch (InterruptedException e) {
				}
			}
			System.out.println(Thread.currentThread().getName() + ":" + --product);
			condition.signalAll();
		} finally {
			lock.unlock();
		}

	}
}

// 生产者
class Productor implements Runnable {
	private Clerk clerk;

	public Productor(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
			clerk.get();
		}
	}

}

// 消费者
class Consumer implements Runnable {

	private Clerk clerk;

	public Consumer(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			clerk.sale();
		}
	}

}
