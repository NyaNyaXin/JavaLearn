package com.cx.juc;
/**
 * �̰߳�����
 * ��Ŀ���жϴ�ӡ���ǡ�one��or ��two��
 * 1.������ͨͬ�������������̣߳���׼��ӡ //one two
 * 2.����Thread.sleep()��getOne(),��ӡ //one two
 * 3.������ͨ����getThree(),��ӡ //three one two
 * 4.������ͨ��ͬ������������number���󣬴�ӡ//two one
 * 5.�޸�getOne()Ϊ��̬ͬ����������ӡ //two one
 * 6.�޸�����������Ϊ��̬ͬ��������һ��number���󣬴�ӡ//one two
 * 7.һ���Ǿ�̬ͬ��������һ���ǷǾ�̬ͬ������������number���󣬴�ӡ//two one
 * 8.������Ϊ��̬ͬ������������number���󣬴�ӡ//one two
 * 
 * �̰߳����Ĺؼ���
 * ��Ǿ�̬��������Ĭ��Ϊthis,��̬����������Ӧ��Classʵ��
 * ��ĳһ��ʱ���ڣ�ֻ����һ���̳߳������������м�������
 * */
public class TestThread8Monitor {
	public static void main(String[] args) {
		Number n = new Number();
		Number n2 = new Number();
		new Thread(new  Runnable() {
			public void run() {
				n.getOne();
			}
		}).start();
		
		new Thread(new  Runnable() {
			public void run() {
				n2.getTwo();
				//n.getTwo();
			}
		}).start();
//		new Thread(new  Runnable() {
//			public void run() {
//				n.getThree();
//			}
//		}).start();
	}

}

class Number{
	public static synchronized void getOne(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("one");
	}
	public  static synchronized void getTwo(){
		System.out.println("two");
	}
	public void getThree(){
		System.out.println("three");
	}
}
