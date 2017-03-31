package com.cx.spring.aop.helloword;

public class ArithmeticCaculatorLoggingImpl implements ArithmeticCaculator {

	@Override
	public int add(int i, int j) {
		System.out.println("The Method add Begins with["+i+","+j+"]");
		int result = i+j;
		System.out.println("The Method add ends with"+result);
		return result;
	}

	@Override
	public int sub(int i, int j) {
		System.out.println("The Method sub Begins with["+i+","+j+"]");
		int result = i-j;
		System.out.println("The Method sub ends with"+result);
		return result;
	}

	@Override
	public int mul(int i, int j) {
		System.out.println("The Method mul Begins with["+i+","+j+"]");
		int result = i*j;
		System.out.println("The Method mul ends with"+result);
		return result;
	}

	@Override
	public int div(int i, int j) {
		System.out.println("The Method div Begins with["+i+","+j+"]");
		int result = i/j;
		System.out.println("The Method div ends with"+result);
		return result;
	}

}
