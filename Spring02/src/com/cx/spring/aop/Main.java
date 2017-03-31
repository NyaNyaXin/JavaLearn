package com.cx.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		// 1.创建Spring的IOC容器
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 2.从IOC容器中获取bean的实例
		ArithmeticCaculator arithmeticCaculator = ctx.getBean(ArithmeticCaculator.class);
		// 3.使用bean
		int result = arithmeticCaculator.add(1, 1);
		System.out.println(result);
		result = arithmeticCaculator.div(1, 1);
		System.out.println(result);
	}

} 
