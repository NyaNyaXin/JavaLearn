package com.cx.thread;

/**
 * ����һ�����̣߳����1-100֮����Ȼ���������ͬ���ģ�Ҳ�����߳�ִ��ͬ���Ĳ���
 * �������̵߳ĵ�һ�ַ�ʽ���̳�java.lang.Thread��
 * */

//1.�����̳�Thread������
class SubThread extends Thread {
	//2.��дThread���run()����,������ʵ�ִ����߳�Ҫ��ɵĹ���
	public void run(){
		for(int i=1;i<=100;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
}

public class TestThread{
	public static void main(String[] args) {
		//3.��������Ķ���
		SubThread su = new SubThread();
		//4.�����̵߳�start�������������̣߳�������Ӧ��run()����
		//һ���߳�ֻ�ܹ�ִ��һ��start����������ͨ��ʵ��������run()����ȥ����һ���߳�
		su.start();
		SubThread su2 = new SubThread();
		su2.start();
		
		for(int i=1;i<=100;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
	
}
