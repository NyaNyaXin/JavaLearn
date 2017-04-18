package com.cx.juc.cas;
/**
 * ģ��CAS�㷨
 * */
public class TestCompareAndSwap {
	public static void main(String[] args) {
		final CompareAndSwap cas = new CompareAndSwap();
		for(int i=0;i<10;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					int expectedValue = cas.get();
					boolean b = cas.compareAndSet(expectedValue, (int)(Math.random()*101));
					System.out.println(b);
				}
			}).start();
		}
	}
}

class CompareAndSwap{
	private int value;
	//��ȡ�ڴ�ֵ
	public synchronized int get(){
		return value;
	}
	//�Ƚ�
	public synchronized int compareAndSwap(int expectedValue,int newValue){
		int oldValue = value;
		if(oldValue==expectedValue){
			this.value = newValue;
		}
		return oldValue;
	}
	
	//����
	public synchronized boolean compareAndSet(int expectedValue,int newValue){
		return expectedValue == compareAndSwap(expectedValue, newValue);
	}
}