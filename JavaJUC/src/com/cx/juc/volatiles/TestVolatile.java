package com.cx.juc.volatiles;

/*
 * һ��volatile�ؼ���:������߳̽��в�����������ʱ�����Ա�֤�ڴ��е������ǿɼ���
 * 		�����synchronized��һ�ֽ�Ϊ��������ͬ������
 * ע�⣺
 * 1��volatile���߱��������ԡ�
 * 2��volatile���ܱ�֤������ԭ����
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
