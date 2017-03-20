package com.cx.springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 1.使用 @RequestMapping注解来映射请求的url
 * 2.返回值会通过视图解析器解析为实际的物理视图，对于InternalResourceViewResolver 视图解析器会做如下解析
 * 通过 prefix + returnVal + 后缀 这样的方式得到实际的物理视图，然后做转发操作
 * /WEB-INF/views/success.jsp
 * **/
@Controller
public class HelloWord {
	@RequestMapping("Helloworld") 
	public String hello() {
		System.out.println("Hello World");
		return "success";
	}

}
