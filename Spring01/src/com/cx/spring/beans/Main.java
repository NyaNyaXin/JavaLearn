package com.cx.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		// //创建helloworld对象
		// HelloWorld hw = new HelloWorld();
		// //对name属性复制
		// hw.setName("chenxin");
		// 调用hello方法

		// 1.创建Spring的IOC容器对象
		// ApplicationContext 代表IOC容器
		// ClassPathXmlApplicationContext：是ApplicationContext接口的实现类，该实现类从类路径
		// 下加载配置文件
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 2.从IOC容器中获取Bean实例
		// 利用id定位到IOC容器中的bean
		// HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld2");
		// 下面这种方式要求IOC容器中配置的bean是唯一的（利用类型返回IOC容器中的bean ）
		HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
		System.out.println(helloWorld);
		// //3.调用方法
		// helloWorld.hello();

		Car car = (Car) ctx.getBean("car");
		System.out.println(car);

		car = (Car) ctx.getBean("car2");
		System.out.println(car);
		
		Person person = (Person) ctx.getBean("person3");
		System.out.println(person.toString());
	}
}
