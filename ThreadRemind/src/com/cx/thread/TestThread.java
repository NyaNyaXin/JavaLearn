package com.cx.thread;

/**
 * 创建一个子线程，完成1-100之间自然数得输出，同样的，也让主线程执行同样的操作
 * 创建多线程的第一种方式：继承java.lang.Thread类
 * */

//1.创建继承Thread的子类
class SubThread extends Thread {
	//2.重写Thread类的run()方法,方法内实现此子线程要完成的功能
	public void run(){
		for(int i=1;i<=100;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
}

public class TestThread{
	public static void main(String[] args) {
		//3.创建子类的对象
		SubThread su = new SubThread();
		//4.调用线程的start方法，启动此线程；调用相应的run()方法
		//一个线程只能够执行一次start方法，不能通过实现类对象的run()方法去启动一个线程
		su.start();
		SubThread su2 = new SubThread();
		su2.start();
		
		for(int i=1;i<=100;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
	
}
