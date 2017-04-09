<%@page import="com.cx.spring.struts2.beans.Person"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		//1.从application域对象中的到IOC容器的实例
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
		//2.从IOC容器中的到bean
		Person person= ctx.getBean(Person.class);
		//3.使用bean
		person.hello();
	%>
</body>
</html>