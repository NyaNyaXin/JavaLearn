package com.cx.springmvc.test;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cx.springmvc.crud.dao.EmployeeDao;
import com.cx.springmvc.crud.entities.Employee;

@Controller
public class SpringMVCTest {
	@Autowired
	private EmployeeDao employeeDao;
	
	@RequestMapping("/testConversionServiceConverter")
	public String testConverter(@RequestParam("employee") Employee employee){
		System.out.println("save:"+employee);
		employeeDao.save(employee);
		return "redirect:emps";
	}
	@ResponseBody
	@RequestMapping("/testJson")
	public Collection<Employee> testJson(){
		return employeeDao.getAll();
	}
}
