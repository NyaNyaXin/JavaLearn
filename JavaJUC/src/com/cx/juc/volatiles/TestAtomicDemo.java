package com.cx.juc.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * һ��i++��ԭ�������⣺i++�Ĳ���ʵ���Ϸ�Ϊ�������衰��-��-д��
 * 		int i =10;
 * 		i=i++;//10
 * 		
 * 		int temp = i;
 * 		i = i+1;
 * 		i = temp;
 * ����ԭ�ӱ�����jdk1.5�Ժ�java.util.concurrent.atomic�����ṩ�˳��õ�ԭ�ӱ�����
 * 			1.volatile��֤�ڴ�ɼ���
 * 			2.CAS(CompareAndSwap)�㷨��֤���ݵ�ԭ����
 * 			  CAS�㷨��Ӳ�����ڲ��������������ݵ�֧��
 * 			  CAS������������������
 * 			    �ڴ�ֵ V
 * 			    Ԥ��ֵ A
 * 			    ����ֵ B
 * 			    ���ҽ���V==Aʱ,V=B,�������κβ���
 * */
public class TestAtomicDemo {
	public static void main(String[] args) {
		AtomicDemo ad = new AtomicDemo();
		for(int i=0;i<10;i++){
			new Thread(ad).start();;
		}
		
	}
}

class AtomicDemo implements Runnable{

//	private volatile int serialNumber = 0;
	private AtomicInteger serialNumber = new AtomicInteger(); 
	@Override
	public void run() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+":"+getSerialNumber());
	}
	public int getSerialNumber(){
		return serialNumber.getAndIncrement();
	}
	
}
