package com.cx.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
//		//����helloworld����
//		HelloWorld hw = new HelloWorld();
//		//��name���Ը���
//		hw.setName("chenxin");
		//����hello����
		
		//1.����Spring��IOC��������
		//ApplicationContext ����IOC����
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2.��IOC�����л�ȡBeanʵ��
		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld2");
		System.out.println(helloWorld);
//		//3.���÷���
//		helloWorld.hello();
	}
}
