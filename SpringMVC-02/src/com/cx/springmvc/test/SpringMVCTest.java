package com.cx.springmvc.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cx.springmvc.crud.dao.EmployeeDao;
import com.cx.springmvc.crud.entities.Employee;

@Controller
public class SpringMVCTest {
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private ResourceBundleMessageSource messageSource;
	@ResponseStatus(reason="测试",value=HttpStatus.NOT_FOUND)
	@RequestMapping("/testResponseStatusExceptionResolver")
	public String testResponseStatusExceptionResolver(@RequestParam("i") int i){
		if(i == 13){
			throw new UsernameNotMatchPasswordException();
		}else{
			System.out.println("testResponseStatusExceptionResolver....");
		}
		return "success";
	}
	//@ExceptionHandler({RuntimeException.class})
//	public ModelAndView arithmeticException2(Exception ex){	
//		System.out.println("[出异常了]："+ex);
//		ModelAndView mv = new ModelAndView("error");
//		mv.addObject("exception", ex);
//		return mv;
//	}
//	
	/**
	 * 1.在@ExceptionHandler 方法的入参中可以加入Exception类型的参数，该参数即对应发生异常对象
	 * 2.@ExceptionHandler 方法的入参中不能传入map，若希望把异常信息传到页面上，则需要把
	 * ModelAndView作为返回值
	 * 3.@ExceptionHandler 标记的异常有优先级的问题
	 * 4.@ControllerAdvice：如果在当前Handler中找不到ExceptionHandler方法来处理当前方法出现的
	 * 异常则将去由@ControllerAdvice注解标记的类中查找@ExceptionHandler标记的方法来处理异常
	 * */
	//@ExceptionHandler({ArithmeticException.class})
	//public String arithmeticException(Exception ex,Map<String,Object> map){
//	public ModelAndView arithmeticException(Exception ex){	
//		System.out.println("出异常了："+ex);
//		ModelAndView mv = new ModelAndView("error");
//		mv.addObject("exception", ex);
//		return mv;
//	}
	@RequestMapping("/testExceptionHandlerExceptionResolver")
	public String testExceptionHandlerExceptionResolver(@RequestParam("i") int i){
		System.out.println("result"+(10/i));
		return "success";
	}

	@RequestMapping("/testFileUpload")
	public String testFileUpload(@RequestParam("desc") String desc, 
			@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println("desc"+desc);
		System.out.println("Filename"+file.getOriginalFilename());
		System.out.println("input"+file.getInputStream());
		return "success";
	}

	@RequestMapping("/i18n")
	public String testi18n(Locale locale) {
		String val = messageSource.getMessage("i18n.user", null, locale);
		System.out.println(val);
		return "i18n";
	}

	@RequestMapping("/testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {

		byte[] body = null;

		ServletContext servletContext = session.getServletContext();
		InputStream in = servletContext.getResourceAsStream("/file/abc.txt");
		body = new byte[in.available()];
		in.read(body);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=abc.txt");

		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}

	@ResponseBody
	@RequestMapping("/testHttpMessageConverter")
	public String testHttpMessageConverter(@RequestBody String body) {
		System.out.println(body);
		return "hello world!" + new Date();
	}

	@RequestMapping("/testConversionServiceConverter")
	public String testConverter(@RequestParam("employee") Employee employee) {
		System.out.println("save:" + employee);
		employeeDao.save(employee);
		return "redirect:emps";
	}

	@ResponseBody
	@RequestMapping("/testJson")
	public Collection<Employee> testJson() {
		return employeeDao.getAll();
	}

}
