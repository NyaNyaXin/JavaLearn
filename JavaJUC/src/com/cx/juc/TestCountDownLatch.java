package com.cx.juc;

import java.util.concurrent.CountDownLatch;

/*
 * ������
 * �����ĳЩ����ʱ��ֻ�����������̵߳�����ȫ����ɣ���ǰ������Ż����ִ��
 * **/
public class TestCountDownLatch {
	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(50);
		LatchDemo ld = new LatchDemo(latch);

		long start = System.currentTimeMillis();
		for (int i = 0; i < 50; i++) {
			new Thread(ld).start();
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("�ķ�ʱ��Ϊ��" + (end - start));
	}
}

class LatchDemo implements Runnable {

	private CountDownLatch latch;

	public LatchDemo(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		synchronized (this) {
			try {
				for (int i = 0; i < 50000; i++) {
					if (i % 2 == 0) {
						System.out.println(i);
					}
				}
			} finally {
				latch.countDown();
			}
		}

	}
}
