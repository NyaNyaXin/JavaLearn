package com.cx.springmvc.crud.handlers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cx.springmvc.crud.dao.DepartmentDao;
import com.cx.springmvc.crud.dao.EmployeeDao;
import com.cx.springmvc.crud.entities.Employee;

@Controller
public class EmployeeHandler {
	@Autowired
	private EmployeeDao emloyeeDao;
	@Autowired
	private DepartmentDao departmentDao;

	@ModelAttribute
	public void getEmployee(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("employee", emloyeeDao.get(id));
		}
	}

	@RequestMapping(value = "/emp", method = RequestMethod.PUT)
	public String update(Employee employee) {
		emloyeeDao.save(employee);
		return "redirect:/emps";
	}

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("employee", emloyeeDao.get(id));
		map.put("departments", departmentDao.getDepartments());
		return "input";
	}

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		emloyeeDao.delete(id);
		return "redirect:/emps";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public String save(@Valid Employee employee, Errors result, Map<String, Object> map) {

		if (result.getErrorCount() > 0) {
			System.out.println("出错了");
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + "----" + error.getDefaultMessage());
			}
			// 若验证出错，则转向定制的页面
			map.put("departments", departmentDao.getDepartments());
			return "input";
		}
		System.out.println("save" + employee);
		emloyeeDao.save(employee);
		return "redirect:/emps";
	}

	@RequestMapping(value = "emp", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {
		map.put("departments", departmentDao.getDepartments());
		map.put("employee", new Employee());
		return "input";
	}

	@RequestMapping("/emps")
	public String list(Map<String, Object> map) {
		map.put("employees", emloyeeDao.getAll());
		return "list";
	}
	// @InitBinder
	// public void initBinder(WebDataBinder binder){
	// binder.setDisallowedFields("lastName");
	// }
}
