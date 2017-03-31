package com.cx.spring.aop.helloword;

public class Main {
	public static void main(String[] args) {
//		ArithmeticCaculator ari =null;
//		ari = new ArithmeticCaculatorLoggingImpl();
		
		ArithmeticCaculator target = new ArithmeticCaculatorImpl();
		ArithmeticCaculator proxy = new ArithmeticCaculatorLoggingProxy(target).getLoggingProxy();
		
		System.out.println(proxy.getClass().getName());
		int result = proxy.add(1, 2);
		System.out.println(result);
	}
}
