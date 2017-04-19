package com.cx.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 1.ReadWriteLock:¶ÁÐ´Ëø
 * Ð´Ð´/¶ÁÐ´		ÐèÒª¡°»¥³â¡±
 * ¶Á¶ÁÔò²»ÐèÒª¡±»¥³â¡°
 * **/
public class TestReadWriteLock {
	public static void main(String[] args) {
		ReadWriteLockDemo rw =new ReadWriteLockDemo();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				rw.set((int) (Math.random()*101));
			}
		},"Write").start();
		
		for(int i=0;i<100;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					rw.get();
				}
			}).start();
		}
	}
}

class ReadWriteLockDemo{
	private int number = 0;
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	//¶Á
	public void get(){
		readWriteLock.readLock().lock();//ÉÏËø
		try {
			System.out.println(Thread.currentThread().getName()+ ":"+number);
		} finally  {
			readWriteLock.readLock().unlock();//ÊÍ·ÅËø
		}
		
		
	}
	//Ð´
	public void set(int number){
		readWriteLock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName());
			this.number = number;
		} finally {
			readWriteLock.writeLock().unlock();
		}
		
	}
}
