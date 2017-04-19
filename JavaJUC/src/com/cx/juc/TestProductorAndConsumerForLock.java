package com.cx.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//�����ߺ������߰���
public class TestProductorAndConsumerForLock {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		Productor productor = new Productor(clerk);
		Consumer consumer = new Consumer(clerk);
		new Thread(productor, "������A").start();
		new Thread(consumer, "������B").start();
		new Thread(productor, "������C").start();
		new Thread(consumer, "������D").start();
	}
}

// ��Ա
class Clerk {
	private int product = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition= lock.newCondition();
	// ����
	public void get() {// ѭ��������2
		lock.lock();
		try {
			while (product >= 1) {// Ϊ�˱�����ٻ������⣬Ӧ������ʹ����ѭ����
				System.out.println("��Ʒ�������޷����");
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

	// ����
	public void sale() {// ѭ��������1 product = 0;
		lock.lock();
		try {
			while (product <= 0) {
				System.out.println("ȱ��");
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

// ������
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

// ������
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
