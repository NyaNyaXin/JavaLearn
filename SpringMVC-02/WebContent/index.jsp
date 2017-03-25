<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#testJson").click(function() {
			var url=this.href;
			var args = {};
			$.post(url,args,function(data){
				for(var i=0;i<data.length;i++){
					var id = data[i].id;
					var lastName = data[i].lastName;
					alert(id+":"+lastName);
				}
			});
			return false;
		});
	})
</script>
</head>
<body>
	<a href="emps">List All Employees</a>
	<br />
	<a href="testJson" id="testJson">Test Json</a>

	<form action="testHttpMessageConverter" method="post"
		enctype="multipart/form-data">
		File:<input type="file" name="file" /> Desc:<input type="text"
			name="desc" /> <input type="submit" value="submit">
	</form>

	<br />

	<a href="testResponseEntity">testResponseEntity</a>
	<!-- 
		关于国际化：
		1.在页面上能够根据浏览器语言设置的情况对文本（不是内容），时间，数值进行本地化处理
		2.可以在bean中获取国际化资源文件locale对应的消息
		3.可以通过超链接切换locale而不再依赖于浏览器的语言设置情况
		
		解决：
		1.使用JSTL的fmt标签
		2.在bean中注入ResourceBundleMessageSource的实例，使用其对应的getMessage方法即可
		3.配置LocalResolver和LocalChangeInterceptor
	 -->

	<br />
	<a href="i18n">I18N PAGE</a>
</body>
</html>