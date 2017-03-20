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
	<a href="springmvc/testRequestParam?username=chenxin&age=11">Test RequestParam</a>
	<br />
	<a href="springmvc/testRequestHeader">Test RequestHeader</a>
	<br />
	<a href="springmvc/testCookieValue">Test CookieValue</a>
</body>
</html>