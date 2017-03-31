package com.cx.spring.beans.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cx.spring.beans.annotation.controller.UserContorller;
import com.cx.spring.beans.annotation.repository.UserRepository;
import com.cx.spring.beans.annotation.service.UserService;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-annotation.xml");
		
//		TestObject to = (TestObject) ctx.getBean("testObject");
//		System.out.println(to);
//		
//		UserContorller uc = (UserContorller) ctx.getBean("userContorller");
//		System.out.println(uc);
//		
//		UserService us = (UserService) ctx.getBean("userService");
//		System.out.println(us);
//		
		UserRepository ur = (UserRepository) ctx.getBean("userRepository");
		System.out.println(ur);
	}
}
