package com.cx.juc;
/**
 * 线程八锁：
 * 题目：判断打印的是”one“or ”two“
 * 1.连个普通同步方法，两个线程，标准打印 //one two
 * 2.新增Thread.sleep()给getOne(),打印 //one two
 * 3.新增普通方法getThree(),打印 //three one two
 * 4.两个普通的同步方法，两个number对象，打印//two one
 * 5.修改getOne()为静态同步方法，打印 //two one
 * 6.修改两个方法均为静态同步方法，一个number对象，打印//one two
 * 7.一个是静态同步方法，一个是非静态同步方法，两个number对象，打印//two one
 * 8.两个均为静态同步方法，两个number对象，打印//one two
 * 
 * 线程八锁的关键：
 * Ⅰ非静态方法的锁默认为this,静态方法的锁对应的Class实例
 * Ⅱ某一个时刻内，只能有一个线程持有锁，无论有几个方法
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
