package com.cx.spring.aop.helloword;

import com.cx.spring.aop.ArithmeticCaculator;
import com.cx.spring.aop.ArithmeticCaculatorImpl;

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
