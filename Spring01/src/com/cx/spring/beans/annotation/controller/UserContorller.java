package com.cx.spring.beans.annotation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cx.spring.beans.annotation.service.UserService;

@Controller
public class UserContorller {
	@Autowired
	private UserService userService;
	
	public void execute(){
		System.out.println("UserContorller execute....");
		userService.add();
	}
}
