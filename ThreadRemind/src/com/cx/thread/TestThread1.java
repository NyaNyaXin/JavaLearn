package com.cx.thread;

/**
 * Thread的常用方法： 1.start()：启动线程并执行相应的run()方法 2.run()：将子线程要执行的代码放入到run()方法中
 * 3.currentThread()：静态的，调取当前线程 4.getName():获取此线程的名字 5.setName():设置此线程的名字
 * 6.yield():调用此方法的线程释放当前CPU的执行权
 * 7.join():在A线程中调用B线程的join()方法，表示，当执行到此方法，A线程停止执行，直至B相称执行完毕，A线程在接着join()之后的代码执行
 * 8.isAlive():判断当前线程是否还存活
 * 9.sleep(Long l):显式的让当前线程睡眠l毫秒
 * 10.线程通信：wait() notify() notifyAll()
 * 
 * 设置线程的优先级
 * 	getPriority() ：返回线程优先值 
	setPriority(int newPriority) ：改变线程的优先级

 */

class SubThread1 extends Thread {
	public void run() {
		for (int i = 1; i <= 100; i++) {
//			try {
//				Thread.currentThread().sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			System.out.println(Thread.currentThread().getName() + ":"+Thread.currentThread().getPriority() +":"+ i);
		}
	}
}

public class TestThread1 {
	public static void main(String[] args) {
		SubThread1 su = new SubThread1();
		su.setName("###########子线程##########");
		su.setPriority(Thread.MAX_PRIORITY);
		su.start();
		Thread.currentThread().setName("#####主线程#####");
		for (int i = 1; i <= 100; i++) {
			System.out.println(Thread.currentThread().getName() + ":"+Thread.currentThread().getPriority()+":"+ i);
//			if (i == 20) {
//				try {
//					su.join();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			// if (i % 10 == 0) {
			// Thread.currentThread().yield();
			// }

		}
		System.out.println(su.isAlive() ? "存活" : "死了");
	}

}
