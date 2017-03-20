package com.cx.springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 1.ʹ�� @RequestMappingע����ӳ�������url
 * 2.����ֵ��ͨ����ͼ����������Ϊʵ�ʵ�������ͼ������InternalResourceViewResolver ��ͼ�������������½���
 * ͨ�� prefix + returnVal + ��׺ �����ķ�ʽ�õ�ʵ�ʵ�������ͼ��Ȼ����ת������
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
