package com.cx.juc;

//�����ߺ������߰���
public class TestProductorAndConsumer {
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

	// ����
	public synchronized void get() {//ѭ��������2
		while (product >= 1) {//Ϊ�˱�����ٻ������⣬Ӧ������ʹ����ѭ����
			System.out.println("��Ʒ�������޷����");
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		} 
			System.out.println(Thread.currentThread().getName() + ":" + ++product);
			this.notifyAll();
	
	}

	// ����
	public synchronized void sale() {//ѭ��������1 product = 0;
		while (product <= 0) {
			System.out.println("ȱ��");
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		} 
			System.out.println(Thread.currentThread().getName() + ":" + --product);
			this.notifyAll();
		
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
