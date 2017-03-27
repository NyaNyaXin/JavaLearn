package com.cx.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		// //����helloworld����
		// HelloWorld hw = new HelloWorld();
		// //��name���Ը���
		// hw.setName("chenxin");
		// ����hello����

		// 1.����Spring��IOC��������
		// ApplicationContext ����IOC����
		// ClassPathXmlApplicationContext����ApplicationContext�ӿڵ�ʵ���࣬��ʵ�������·��
		// �¼��������ļ�
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 2.��IOC�����л�ȡBeanʵ��
		// ����id��λ��IOC�����е�bean
		// HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld2");
		// �������ַ�ʽҪ��IOC���������õ�bean��Ψһ�ģ��������ͷ���IOC�����е�bean ��
		HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
		System.out.println(helloWorld);
		// //3.���÷���
		// helloWorld.hello();

		Car car = (Car) ctx.getBean("car");
		System.out.println(car);

		car = (Car) ctx.getBean("car2");
		System.out.println(car);
		
		Person person = (Person) ctx.getBean("person3");
		System.out.println(person.toString());
	}
}
