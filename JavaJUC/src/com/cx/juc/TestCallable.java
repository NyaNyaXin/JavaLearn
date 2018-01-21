package com.cx.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
 * ����ִ���̵߳ķ�ʽ����ʵ��Callable�ӿ�.�����ʵ��Runnable�ӿڵ�ʵ�ַ�ʽ�����������з���ֵ���������׳��쳣
 * 
 * ִ��Callable�ķ�ʽ����ҪFutureTaskʵ�����֧�֣����ڽ�����������FutureTask��Future�ӿڵ�ʵ����
 * **/
public class TestCallable {
	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();
		// 1.ִ��Callable�ķ�ʽ����ҪFutureTaskʵ�����֧�֣����ڽ���������
		FutureTask<Integer> result = new FutureTask<>(td);
		new Thread(result).start();

		// 2.�����߳������Ľ��
		try {
			System.out.println(result.get());//FutureTaskҲ�����ڱ����Ĳ���
			System.out.println("����������������������������������������������������������������������������");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class ThreadDemo implements  Callable<Integer>{

	
	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for(int i=0;i<=100;i++) {
			sum += i;
		}
		return sum;
	}


}
