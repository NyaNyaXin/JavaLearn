package com.cx.juc.volatiles;

/*
 * 一、volatile关键字:当多个线程进行操作共享数据时，可以保证内存中的数据是可见的
 * 		相较于synchronized是一种较为轻量级的同步策略
 * 注意：
 * 1、volatile不具备“互斥性”
 * 2、volatile不能保证变量的原子性
 * **/
public class TestVolatile {
	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();
		new Thread(td).start();
		while(true){
			
				if(td.isFlag()){
					System.out.println("-------------------");
					break;
				}
			
			
		}
	}
}

class ThreadDemo implements Runnable {

	private volatile boolean flag = false;

	@Override
	public void run() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			
		}
		flag = true;
		System.out.println("flag=" + flag);
	}

	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
