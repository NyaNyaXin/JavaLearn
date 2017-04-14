package com.cx.runnable;

public class TestThread {
	public static void main(String[] args) {
		//创建实现Runnable接口累的对象
		PrintNum1 p = new PrintNum1();
		//要想启动多线程
		Thread t1 = new Thread(p);
		t1.start();//启动线程执行Thread对象生成时构造器形参的的对象的run方法
		t1.setName("aaa");
		for(int i=1;i<=100;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
}

//实现Runnable接口
class PrintNum1 implements Runnable{

	@Override
	//实现接口的抽象方法
	public void run() {
		for(int i=1;i<=100;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
	
}