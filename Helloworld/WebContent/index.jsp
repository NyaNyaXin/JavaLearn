<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="springmvc/testMethod" method="post">
		<input type="submit" value="submit">
	</form>
	<br>
	<a href="springmvc/testMethod">testMethod</a>
	<br>
	<a href="springmvc/testRequestMapping">testRequestMapping</a>.
	<br>
	<a href="Helloworld">Hello World</a>
	<br />
	<a href="springmvc/testParamsAndHeaders?username=chenxin&age=11">testParamsAndHeaders</a>
	<br />
	<a href="springmvc/testAntPath/*/abc">testAntPath</a>
	<br />
	<a href="springmvc/testPathVariable/1">testPathVariable</a>

	<br />
	<a href="springmvc/testRest/1">Test REST GET</a>
	<br />
	<form action="springmvc/testRest" method="post">
		<input type="submit" value="TestREST POST">
	</form>
	<br />
	<form action="springmvc/testRest/1">
		<input type="hidden" name="_method" value="DELETE"> <input
			type="submit" value="TestREST DELETE">
	</form>
	<br />
	<form action="springmvc/testRest/1">
		<input type="hidden" name="_method" value="PUT"> <input
			type="submit" value="TestREST PUT">
	</form>
	<br />
	<a href="springmvc/testRequestParam?username=chenxin&age=11">Test
		RequestParam</a>
	<br />
	<a href="springmvc/testRequestHeader">Test RequestHeader</a>
	<br />
	<a href="springmvc/testCookieValue">Test CookieValue</a>
	<br />
	<form action="springmvc/testPojo">
		username: <input type="text" name="username"> <br /> password:
		<input type="password" name="password"> <br /> email: <input
			type="text" name="email"> <br /> age: <input type="text"
			name="age"> <br /> city: <input type="text"
			name="address.city"> <br /> province: <input type="text"
			name="address.province"> <br /> <input type="submit"
			name="Submit">
	</form>
	<br />
	<a href="springmvc/testServletApI">Test ServletApI</a>
	<br />
	<a href="springmvc/testModelAndView">Test ModelAndView</a>
	<br />
	<a href="springmvc/testMap">Test Map</a>
	<br />
	<a href="springmvc/testSessionAttribute">Test SessionAttribute</a>


	<br />
	<!-- 模拟修改操作
		 1.原始数据为：1,Tom,123456,tom@qq.com,12
		 2.密码不能被修改
		 3.表单回显，模拟操作直接在表单填写对应的属性值
	 -->
	<form action="springmvc/testModelAttribute" method="post">
		<input type="hidden" name="id" value="1"/>
		username:<input type="text" name="username" value="Tom" />
		<br />
		<br />
		email:<input type="text" name="email" value="tom@qq.com" />
		<br />
		age:<input type="text" name="age" value="12" />
		<input type="submit" value="Submit" />
	</form>

</body>
</html>