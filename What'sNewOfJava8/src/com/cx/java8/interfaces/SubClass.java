package com.cx.java8.interfaces;

public class SubClass implements MyFunction,MyInterface{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return MyInterface.super.getName();
	}
	
}
