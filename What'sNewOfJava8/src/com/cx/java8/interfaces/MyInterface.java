package com.cx.java8.interfaces;

public interface MyInterface {
	default String getName(){
		return "xixixi";
	}
	
	public static void show(){
		System.out.println("接口中的静态方法");
	}
}
