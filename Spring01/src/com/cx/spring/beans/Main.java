package com.cx.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
//		//创建helloworld对象
//		HelloWorld hw = new HelloWorld();
//		//对name属性复制
//		hw.setName("chenxin");
		//调用hello方法
		
		//1.创建Spring的IOC容器对象
		//ApplicationContext 代表IOC容器
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2.从IOC容器中获取Bean实例
		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld2");
		System.out.println(helloWorld);
//		//3.调用方法
//		helloWorld.hello();
	}
}
