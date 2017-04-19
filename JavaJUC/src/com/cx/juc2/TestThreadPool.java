package com.cx.juc2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * һ���̳߳�:�ṩ��һ���̶߳��У������б��������еȴ�״̬���̡߳������˴����������̵߳Ķ��⿪�����������Ӧ�ٶ�
 * 
 * �����̳߳ص���ϵ�ṹ java.util.concurrent.Executor�������̵߳�ʹ������ȵĸ��ӿ�
 * |--**ExecutorService�ӽӿڣ��̳߳ص���Ҫ�ӿ� |--ThreadPoolExecutor���̳߳ص�ʵ����
 * |--ScheduledExecutorService�ӽӿڣ������̵߳ĵ���
 * |--ScheduledThreadPoolExecutor���̳���ThreadPoolExecutor��ʵ����ScheduledExecutorService
 * ���������ࣺExecutors ExecutorService newFixedThreadPool():�����̶���С���̳߳�
 * ExecutorService newCachedThreadPool()�������̳߳أ��̳߳ص��������̶������Ը��������Զ���������
 * ExecutorService newSingleThreadExecutor()�����������̳߳ء��̳߳���ֻ��һ���߳�
 * 
 * ScheduledExecutorService newScheduleThreadPool()�������̶���С���̣߳������ӳٻ�ʱ��ִ������
 */
public class TestThreadPool {
	public static void main(String[] args) throws Exception {

		// 1.�����̳߳�
		ExecutorService pool = Executors.newFixedThreadPool(5);
		List<Future<Integer>> list = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			Future<Integer> fu = pool.submit(new Callable<Integer>(){
				
				@Override
				public Integer call() throws Exception {
				int sum = 0;
				for (int i = 0; i <= 100; i++) {
					sum+=i;
				}
					return sum;
				}
				
			});
			list.add(fu);
			
		}
		for(Future<Integer> future :list){
			System.out.println(future.get());
		}
		
		pool.shutdown();
		/*ThreadPoolDemo tpd = new ThreadPoolDemo();
		// 2.Ϊ�̳߳��е��̷߳�������
		for (int i = 0; i < 10; i++) {
			pool.submit(tpd);
		}

		// 3.�ر��̳߳�
		pool.shutdown();*/
	}
	// new Thread(tpd).start();
	// new Thread(tpd).start();
}

class ThreadPoolDemo implements Runnable {
	private int i = 0;

	@Override
	public void run() {
		while (i < 100) {
			System.out.println(Thread.currentThread().getName() + ":" + i++);
		}
	}
}
