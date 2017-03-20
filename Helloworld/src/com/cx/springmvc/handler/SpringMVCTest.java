package com.cx.springmvc.handler;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cx.springmvc.entities.User;

//@SessionAttributes(value = { "user" }, types = { String.class })
@Controller
@RequestMapping("/springmvc")
public class SpringMVCTest {

	private static final String SUCCESS = "success";
	/**
	 * 由@ModelAttribute 标记的方法，会在每个目标方法执行之前被SpringMVC调用
	 * **/
	@ModelAttribute
	public void getUser(@RequestParam(value="id",required=false) Integer id,
			Map<String,Object> map){
		if(id!=null){
			//模拟从数据库中获取对象
			User user = new User(1,"Tom","123456","tom@qq.com",12);
			System.out.println("从数据库中获取一个对象"+user);
			map.put("user", user);
		}
	}
	/**
	 * 运行流程
	 * 1.执行 @ModelAttribute 注释修饰的方法：从数据库中取出对象，把对象放入到map中。键为：user
	 * 2.SpringMVC从Map中取出user对象，并把表单的请求参数赋给User对象对应的属性
	 * 3.SpringMVC把上述对象传入目标方法的参数
	 * 
	 * 注意在 @ModelAttribute 修饰的方法中，放入到Map时的键需要和目标方法入参类型的第一个字
	 * 母小写的字符串一致
	 * 
	 * SpringMVC确定目标方法POJO类型入参的过程
	 * 1.确定一个key
	 * 1)若目标方法的POJO类型的参数没有使用@ModelAttribute注解修饰，则key为POJO类名首字母小写
	 * 2)若使用了@ModelAttribute注解修饰，则key为value属性的值
	 * 2.在implicitModel中查找key对应的对象，如果存在，则作为入参传入
	 * 1)若在@ModelAttribute标记的方法中在Map中保存过且key和1确定的key一致则会获取到
	 * 3.若不存在key对应的对象，则检查当前的handler是否使用  @SessionAttribute 注解修饰
	 * 若使用了该注解，并且该注解的value属性值中包含了key，则或从HttpSession中来获取key对应的value值，若存在
	 * 则直接传入到目标方法的入参中。若不存在则抛异常
	 * 4.若Handler没有被@SessionAttribute 标识或@SessionAttribute的value值中不包含key则会通过反射来创建
	 * POJO类型的参数，传入为目标方法的参数
	 * 5.SpringMVC会把key和POJO对象保存到implicitModel中，进而保存到request中。
	 * 
	 * 源码分析流程
	 * 1.调用@ModelAttribute注解修饰的方法。实际上把@ModelAttribute方法中的Map中的数据放在了implicitModel中
	 * 2.解析请求处理器的目标参数，实际上该目标参数来自于WebDataBinder对象的target属性
	 * 1)创建WebDataBinder对象
	 * ①确定objecName属性：若传入的attrName属性值为“”，则objectName为类名第一个字母小写
	 * 注意：attrName若目标方法的POJO属性使用了 @ModelAttribute 来修饰则attrName即为 @ModelAttribute 的
	 * value属性值
	 * ②确定target属性：
	 * >在implicitModel中查找attrName对应的属性值，若存在，返回
	 * >如果不存在则验证当前handler是否用了 @SessionAttribute 进行修饰，若使用了则尝试从session中获取
	 * attrName所对应的属性值，若没有则抛出异常
	 * >如果Handler没有使用@SessionAttribute进行修饰或@SessionAttribute中没有使用value值指定的键
	 * 和attrName相互匹配，则通过反射创建了POJO对象
	 * 2)SpringMVC把表单的请求参数赋给了WebDataBinder的target对应的属性
	 * 3)SpringMVC会把WebDataBinder的attrName和target给到implicitModel，进而传到request域对象中
	 * 4)把WebDataBinder的target作为参数传递给目标方法的入参
	 * **/
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user){
		System.out.println("修改："+user);
		return SUCCESS;
	}

	/**
	 * @SessionAttributes 
	 * 除了可以通过属性名指定需要放到会话中的属性外(实际上使用的是value属性值)，
	 * 还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中(实际上使用的是type属性值)
	 * 这个注解只能放在类的上面，而不能放在方法的上面
	 **/
	@RequestMapping("/testSessionAttribute")
	public String testSessionAttribute(Map<String, Object> map) {
		User user = new User("tom", "123", "chen@qq.com", 11);
		map.put("user", user);
		map.put("ahe", "aadhuw");
		return SUCCESS;
	}

	/**
	 * 目标方法可以添加Map(实际上也可以是Model类型或ModelMap类型)类型的参数
	 **/
	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map) {
		System.out.println(map.getClass().getName());
		map.put("names", Arrays.asList("tom", "jerry", "mike"));
		return SUCCESS;
	}

	/**
	 * 目标方法的返回值可以使ModelAndView类型。 其中可以包含视图和模型信息
	 * SpringMVC会把ModelAndView的model中的数据放入到request域对象中
	 * 
	 **/
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView() {
		String viewName = SUCCESS;
		ModelAndView modelAndView = new ModelAndView(viewName);
		// 添加模型数据到ModelAndView中
		modelAndView.addObject("time", new Date());
		return modelAndView;
	}

	/**
	 * 可以使用servlet原生的API作为目标方法的参数 具体支持以下类型 HttpServletRequest
	 * HttpServletResponse HttpSession java.security.Principal Locale
	 * InputStream OutputStream Reader Writer
	 * 
	 * @throws IOException
	 **/
	@RequestMapping("/testServletApI")
	public void testServletApI(HttpServletRequest request, HttpServletResponse response, Writer out)
			throws IOException {
		System.out.println("testServletApI:" + request + "," + response);
		// return SUCCESS;
		out.write("hello spring");
	}

	/**
	 * Spring MVC 会按请求参数名和 POJO属性名进行自动匹 配，自动为该对象填充属性值。支持级联属性。
	 * 如：dept.deptId、dept.address.tel 等
	 */
	@RequestMapping("testPojo")
	public String testPojo(User user) {
		System.out.println("testPojo" + user);
		return SUCCESS;
	}

	/**
	 * 了解
	 * 
	 * @CookieValue 映射一个cookie值，属性同@RequestParam
	 */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String sid) {
		System.out.println("testCookieValue" + sid);
		return SUCCESS;
	}

	/**
	 * 了解： 用法同@RequestParam 作用是映射请求头信息
	 */
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value = "Accept-Language") String al) {
		System.out.println("testRequestHeader" + al);
		return SUCCESS;
	}

	/**
	 * @RequestParam来映射请求参数， 其中value即请求参数的参数名 required 该参数是否为必须，默认为true
	 * defaultValue 请求参数的默认值
	 **/
	@RequestMapping(value = "/testRequestParam")
	public String testRequestParam(@RequestParam(value = "username") String un,
			@RequestParam(value = "age", required = false, defaultValue = "0") Integer age) {
		System.out.println("testRequestParam:" + un + age);
		return SUCCESS;
	}

	/**
	 * REST风格的URL 以CRUD为例 新增 /order POST 修改 /order/1 PUT update?id=1 获取 /order/1
	 * GET get?id=1 删除 /order/1 DELETE delete?id=1
	 * 
	 * 如何发送PUT请求和DELETE请求 1.需要配置HiddenHttpMethodFilter 2.需要发送POST请求
	 * 3.需要在发送POST请求时携带一个name="_method"的隐藏域，值为DELETE或PUT
	 * 
	 * 在SpringMVC的目标方法中如何得到id 使用@PathVariable注解
	 */
	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.GET)
	public String testREST(@PathVariable("id") Integer id) {
		System.out.println("testREST GET" + id);
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest", method = RequestMethod.POST)
	public String testREST() {
		System.out.println("testREST POST");
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
	public String testRESTDel(@PathVariable("id") Integer id) {
		System.out.println("testREST DELETE" + id);
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
	public String testRESTPut(@PathVariable("id") Integer id) {
		System.out.println("testREST put" + id);
		return SUCCESS;
	}

	/**
	 * @PathVariabl 可以来映射URL中的占位符到目标方法的参数中 T
	 */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println("testPathVariable" + id);
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
