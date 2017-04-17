package com.cx.java8.interfaces;

public interface MyFunction {
	//默认方法，用default修饰
	default String getName(){
		return "hahaha";
	}
}
