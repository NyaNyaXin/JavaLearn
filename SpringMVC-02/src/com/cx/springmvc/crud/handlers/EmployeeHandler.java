package com.cx.springmvc.crud.handlers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cx.springmvc.crud.dao.DepartmentDao;
import com.cx.springmvc.crud.dao.EmployeeDao;
import com.cx.springmvc.crud.entities.Employee;

@Controller
public class EmployeeHandler {
	@Autowired
	private EmployeeDao emloyeeDao;
	@Autowired
	private DepartmentDao departmentDao;
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String save(Employee employee){
		emloyeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@RequestMapping(value="emp",method=RequestMethod.GET)
	public String input(Map<String, Object> map){
		map.put("departments", departmentDao.getDepartments());
		map.put("employee", new Employee());
		return "input";
	}
	@RequestMapping("/emps")
	public String list(Map<String, Object> map){
		map.put("employees", emloyeeDao.getAll());
		return "list";
	}
}
