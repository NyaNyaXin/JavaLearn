package com.cx.springmvc;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	public void HelloWorld(){
		System.out.println("Hello World Constructor");
	}
	public UserService(){
		System.out.println("UserService Constructor");
	}
}
