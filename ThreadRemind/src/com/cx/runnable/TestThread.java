package com.cx.runnable;

public class TestThread {
	public static void main(String[] args) {
		//����ʵ��Runnable�ӿ��۵Ķ���
		PrintNum1 p = new PrintNum1();
		//Ҫ���������߳�
		Thread t1 = new Thread(p);
		t1.start();//�����߳�ִ��Thread��������ʱ�������βεĵĶ����run����
		t1.setName("aaa");
		for(int i=1;i<=100;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
}

//ʵ��Runnable�ӿ�
class PrintNum1 implements Runnable{

	@Override
	//ʵ�ֽӿڵĳ��󷽷�
	public void run() {
		for(int i=1;i<=100;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
	
}