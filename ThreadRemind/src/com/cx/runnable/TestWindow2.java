package com.cx.runnable;
/*
 *  线程安全问题
 *  
 *  解决：
 *  同步代码块
 *  synchronized{
 *  	//需要被同步的代码块
 *  }
 *  同步方法
 * ***/
class Window3 implements Runnable {
	
	

	int ticker = 100;
	
	Object obj = new Object();

	@Override
	public void run() {
		synchronized (obj) {
			while (true) {
				if (ticker > 0) {
					System.out.println(Thread.currentThread().getName() + "票号" + ticker--);
				} else {
					break;
				}
			}
		}
		
	}

}

public class TestWindow2 {
	public static void main(String[] args) {
		Window3 w = new Window3();
		Thread t1 = new	Thread(w);		Thread t2 = new	Thread(w);
		Thread t3 = new	Thread(w);
		
		t1.setName("window1");
		t2.setName("window2");
		t3.setName("window3");
		t1.start();
		t2.start();
		t3.start();
	}
	
}
