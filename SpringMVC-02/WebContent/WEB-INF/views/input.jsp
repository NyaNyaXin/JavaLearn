<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- 1.使用form标签的原因：
		  快速开发出表单页面，可以更方便的进行表单值的回显
		 2.注意：可以通过 modelAttribute 属性指定绑定的模型属性，若 
			没有指定该属性，则默认从 request 域对象中读取
			command 的表单 bean，如果该属性值也不存在，则会
			发生错误。
	 -->
	<form:form action="emp" method="POST" modelAttribute="employee">
		<!-- path属性对一个html表单标签的name属性 -->
	 	LastName:<form:input path="lastName" />
		<br />
	 	Email:<form:input path="email" />
		<br />
		<%
			Map<String, String> genders = new HashMap();
				genders.put("1", "Male");
				genders.put("0", "Female");
				request.setAttribute("genders", genders);
		%>
		Gender:
		<br/>
		<form:radiobuttons path="gender" items="${genders }" delimiter="<br/>"/>
		<br />
		Department:<form:select path="department.id" items="${departments }"
			itemLabel="departmentName" itemValue="id"></form:select>
		<input type="submit" value="Submit">
	</form:form>
</body>
</html>