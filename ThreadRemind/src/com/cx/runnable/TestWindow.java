package com.cx.runnable;

class Window1 implements Runnable {

	int ticker = 100;

	@Override
	public void run() {
		while (true) {
			if (ticker > 0) {
				System.out.println(Thread.currentThread().getName() + "Æ±ºÅ" + ticker--);
			} else {
				break;
			}
		}
	}

}

public class TestWindow {
	public static void main(String[] args) {
		Window1 w = new Window1();
		Thread t1 = new	Thread(w);
		Thread t2 = new	Thread(w);
		Thread t3 = new	Thread(w);
		
		t1.setName("window1");
		t2.setName("window2");
		t3.setName("window3");
		t1.start();
		t2.start();
		t3.start();
	}
	
}
