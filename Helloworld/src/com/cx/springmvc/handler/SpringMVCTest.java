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
	 * �˽�
	 * @CookieValue ӳ��һ��cookieֵ������ͬ@RequestParam
	 * */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String sid){
		System.out.println("testCookieValue"+sid);
		return SUCCESS;
	}
	/**
	 * �˽⣺
	 * �÷�ͬ@RequestParam
	 * ������ӳ������ͷ��Ϣ
	 * */
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="Accept-Language") String al){
		System.out.println("testRequestHeader"+al);
		return SUCCESS;
	}
	
	/**
	 * @RequestParam��ӳ�����������
	 * ����value����������Ĳ�����
	 * required	�ò����Ƿ�Ϊ���룬Ĭ��Ϊtrue
	 * defaultValue ���������Ĭ��ֵ
	 * **/
	@RequestMapping(value= "/testRequestParam")
	public String testRequestParam(@RequestParam(value="username") String un,
			@RequestParam(value = "age",required=false,defaultValue="0") Integer age){
		System.out.println("testRequestParam:" + un+ age );
		return SUCCESS;
	}
	
	/**
	 * REST����URL
	 * ��CRUDΪ��
	 * ���� /order POST
	 * �޸� /order/1 PUT			update?id=1
	 * ��ȡ /order/1 GET			get?id=1
	 * ɾ�� /order/1 DELETE		delete?id=1
	 * 
	 * ��η���PUT�����DELETE����
	 * 1.��Ҫ����HiddenHttpMethodFilter
	 * 2.��Ҫ����POST����
	 * 3.��Ҫ�ڷ���POST����ʱЯ��һ��name="_method"��������ֵΪDELETE��PUT
	 * 
	 * ��SpringMVC��Ŀ�귽������εõ�id
	 * ʹ��@PathVariableע��
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
	 * @PathVariabl ������ӳ��URL�е�ռλ����Ŀ�귽���Ĳ�����
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
	 * �˽⣺����ʹ��pramas��headers�����Ӿ�ȷ��ӳ������������֧�ּ򵥵ı��ʽ
	 * 
	 */
	@RequestMapping(value = "testParamsAndHeaders", params = { "username", "age!=10" }, headers = {
			"Accept-Language=zh-CN,zh;q=0.8,en;q=0.6" })
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}

	/**
	 * ʹ��value������ָ������url ʹ��method������ָ������ʽ
	 */
	@RequestMapping(value = "/testMethod", method = RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}

	/**
	 * 1.@RequestMapping�ȿ������η�����Ҳ���������� 2. 1���ඨ�崦���ṩ����������ӳ����Ϣ�������webӦ�õĸ�Ŀ¼
	 * 2�����������ṩ��һ����ϸ��ӳ����Ϣ��������ඨ�����URL��
	 * ���ඨ�崦δ���@RequestMapping���򷽷�����ǵ�URL�����webӦ�õĸ�Ŀ¼
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
}
