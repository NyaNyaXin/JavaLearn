package com.cx.java8.interfaces;

public interface MyInterface {
	default String getName(){
		return "xixixi";
	}
	
	public static void show(){
		System.out.println("�ӿ��еľ�̬����");
	}
}
