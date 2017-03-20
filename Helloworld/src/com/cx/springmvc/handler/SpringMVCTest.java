package com.cx.springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/springmvc")
public class SpringMVCTest {

	private static final String SUCCESS = "success";
	/**
	 * 了解
	 * @CookieValue 映射一个cookie值，属性同@RequestParam
	 * */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String sid){
		System.out.println("testCookieValue"+sid);
		return SUCCESS;
	}
	/**
	 * 了解：
	 * 用法同@RequestParam
	 * 作用是映射请求头信息
	 * */
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="Accept-Language") String al){
		System.out.println("testRequestHeader"+al);
		return SUCCESS;
	}
	
	/**
	 * @RequestParam来映射请求参数，
	 * 其中value即请求参数的参数名
	 * required	该参数是否为必须，默认为true
	 * defaultValue 请求参数的默认值
	 * **/
	@RequestMapping(value= "/testRequestParam")
	public String testRequestParam(@RequestParam(value="username") String un,
			@RequestParam(value = "age",required=false,defaultValue="0") Integer age){
		System.out.println("testRequestParam:" + un+ age );
		return SUCCESS;
	}
	
	/**
	 * REST风格的URL
	 * 以CRUD为例
	 * 新增 /order POST
	 * 修改 /order/1 PUT			update?id=1
	 * 获取 /order/1 GET			get?id=1
	 * 删除 /order/1 DELETE		delete?id=1
	 * 
	 * 如何发送PUT请求和DELETE请求
	 * 1.需要配置HiddenHttpMethodFilter
	 * 2.需要发送POST请求
	 * 3.需要在发送POST请求时携带一个name="_method"的隐藏域，值为DELETE或PUT
	 * 
	 * 在SpringMVC的目标方法中如何得到id
	 * 使用@PathVariable注解
	 * */
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.GET)
	public String testREST(@PathVariable("id") Integer id){
		System.out.println("testREST GET"+id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest",method=RequestMethod.POST)
	public String testREST(){
		System.out.println("testREST POST");
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.DELETE)
	public String testRESTDel(@PathVariable("id") Integer id){
		System.out.println("testREST DELETE"+id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.PUT)
	public String testRESTPut(@PathVariable("id") Integer id){
		System.out.println("testREST put"+id);
		return SUCCESS;
	}
	
	/**
	 * @PathVariabl 可以来映射URL中的占位符到目标方法的参数中
	 * T
	 * */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id){
		System.out.println("testPathVariable"+id);
		return SUCCESS;
	}
	@RequestMapping("/testAntPath/*/abc")
	public String testAntPath() {
		System.out.println("testAntPath");
		return SUCCESS;
	}

	/**
	 * 了解：可以使用pramas和headers来更加精确的映射请求，这两个支持简单的表达式
	 * 
	 */
	@RequestMapping(value = "testParamsAndHeaders", params = { "username", "age!=10" }, headers = {
			"Accept-Language=zh-CN,zh;q=0.8,en;q=0.6" })
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}

	/**
	 * 使用value属性来指定请求url 使用method属性来指定请求方式
	 */
	@RequestMapping(value = "/testMethod", method = RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}

	/**
	 * 1.@RequestMapping既可以修饰方法，也可以修饰类 2. 1）类定义处：提供初步的请求映射信息。相对于web应用的根目录
	 * 2）方法处：提供进一步的细分映射信息。相对于类定义出的URL。
	 * 若类定义处未标记@RequestMapping，则方法处标记的URL相对于web应用的根目录
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
}
