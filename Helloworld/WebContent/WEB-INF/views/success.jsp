<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h4>Success Page</h4>
	time: ${requestScope.time }
	<br>
	names: ${requestScope.names }
	<br>
	RequestUser: ${requestScope.user }
	<br>
	SessionUser: ${sessionScope.user }
	<br>
	RequestAhe: ${requestScope.ahe }
	<br>
	SessionAhe: ${sessionScope.ahe }
	<br>
	<fmt:message key="i18n.username"></fmt:message>
	<br>
	<fmt:message key="i18n.password"></fmt:message>
</body>
</html>