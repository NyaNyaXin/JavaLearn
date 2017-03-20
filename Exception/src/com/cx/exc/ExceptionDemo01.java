package com.cx.exc;

class Exc{
	int i=10;
}

public class ExceptionDemo01 {
	public static void main(String[] args) {
		int a = 10;
		int b = 10;
		int temp = 0;
		try {
			 temp = a/b;
		} catch (ArithmeticException e) {
			System.out.println(e);
		}
		
		System.out.println(temp);
	}
}
