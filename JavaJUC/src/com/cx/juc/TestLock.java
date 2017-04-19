package com.cx.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * һ�����ڽ�����̰߳�ȫ����ķ�ʽ
 * synchronized:��ʽ��
 * 
 * 1.ͬ�������
 * 2.ͬ������
 * 
 * jdk1.5�Ժ�
 * 3.ͬ����Lock
 * ע�⣺��һ����ʾ������Ҫͨ��lock()��������,����ͨ��unlock()���������ͷ���
 * */
public class TestLock {
	public static void main(String[] args) {
		Ticket tk = new Ticket();
		new Thread(tk, "һ�Ŵ���").start();
		new Thread(tk, "���Ŵ���").start();
		new Thread(tk, "���Ŵ���").start();
	}
}

class Ticket implements Runnable {
	private int tick = 100;
	private Lock lock = new ReentrantLock();

	@Override
	public void run() {
		while (true) {
			lock.lock();//����
			try {
				if (tick > 0) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "�����Ʊ����ƱΪ��" + --tick);
				}

			} finally {
				lock.unlock();//�ͷ���
			}

		}
	}

}
