package com.cx.thread;

/**
 * Thread�ĳ��÷����� 1.start()�������̲߳�ִ����Ӧ��run()���� 2.run()�������߳�Ҫִ�еĴ�����뵽run()������
 * 3.currentThread()����̬�ģ���ȡ��ǰ�߳� 4.getName():��ȡ���̵߳����� 5.setName():���ô��̵߳�����
 * 6.yield():���ô˷������߳��ͷŵ�ǰCPU��ִ��Ȩ
 * 7.join():��A�߳��е���B�̵߳�join()��������ʾ����ִ�е��˷�����A�߳�ִֹͣ�У�ֱ��B���ִ����ϣ�A�߳��ڽ���join()֮��Ĵ���ִ��
 * 8.isAlive():�жϵ�ǰ�߳��Ƿ񻹴��
 * 9.sleep(Long l):��ʽ���õ�ǰ�߳�˯��l����
 * 10.�߳�ͨ�ţ�wait() notify() notifyAll()
 * 
 * �����̵߳����ȼ�
 * 	getPriority() �������߳�����ֵ 
	setPriority(int newPriority) ���ı��̵߳����ȼ�

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
		su.setName("###########���߳�##########");
		su.setPriority(Thread.MAX_PRIORITY);
		su.start();
		Thread.currentThread().setName("#####���߳�#####");
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
		System.out.println(su.isAlive() ? "���" : "����");
	}

}
