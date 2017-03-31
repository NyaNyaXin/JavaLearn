package com.cx.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		// 1.����Spring��IOC����
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 2.��IOC�����л�ȡbean��ʵ��
		ArithmeticCaculator arithmeticCaculator = ctx.getBean(ArithmeticCaculator.class);
		// 3.ʹ��bean
		int result = arithmeticCaculator.add(1, 1);
		System.out.println(result);
		result = arithmeticCaculator.div(1, 1);
		System.out.println(result);
	}

} 
