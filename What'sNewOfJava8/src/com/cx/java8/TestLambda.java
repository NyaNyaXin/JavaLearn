package com.cx.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;

public class TestLambda {
	
	//原来的匿名内部类
	@Test
	public void test1(){
		Comparator<Integer> com = new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return compare(o1, o2);
			}
		};
		TreeSet<Integer> ts = new TreeSet<>(com);
	}
	
	//Lanbda表达式
	@Test
	public void test2(){
		Comparator<Integer> com = (x,y) -> Integer.compare(x, y);
		TreeSet<Integer> ts = new TreeSet<>(com);
	}
	
	List<Employee> employees = Arrays.asList(
			new Employee("chen",18,999.99),
			new Employee("xin",28,939.99),
			new Employee("ding",38,999.99),
			new Employee("jian",8,929.99),
			new Employee("hui",50,999.99)
			);
	
	@Test
	public void test3(){
		List<Employee> list = fliterEmployees(employees);
		for(Employee emp:list){
			System.out.println(emp);
		}
	}
	//需求，获取当前公司中员工年龄大于35的员工信息
	public List<Employee> fliterEmployees(List<Employee> list){
		List<Employee> emps = new ArrayList<>();
		for(Employee emp:list){
			if(emp.getAge()>=35){
				emps.add(emp);
			}
		}
		return emps;
	}
	
	//需求，获取当前公司中员工工资大于990的员工信息
	public List<Employee> fliterEmployees2(List<Employee> list){
		List<Employee> emps = new ArrayList<>();
		for(Employee emp:list){
			if(emp.getAge()>=990){
				emps.add(emp);
			}
		}
		return emps;
	}
	
	
	@Test
	public void test4(){
		List<Employee> list =  filterEmployees(employees, new FilterEmployeeByAge());
		for(Employee employee:list){
			System.out.println(employee);
		}
		System.out.println("_________________________________");
		List<Employee> list1 = filterEmployees(employees, new FilterEmployeeBySalary());
		for(Employee employee:list1){
			System.out.println(employee);
		}
	}
	//优化方式一:策略设计模式
	public List<Employee> filterEmployees(List<Employee> list,MyPredicate<Employee> mp){
		List<Employee> emps = new ArrayList<>();
		for(Employee employee:list){
			if(mp.test(employee)){
				emps.add(employee);
			}
		}
		return emps;
	}
	
	//优化方式二：匿名内部类
	
	@Test
	public void test5(){
		List<Employee> list = filterEmployees(employees, new MyPredicate<Employee>() {
			
			@Override
			public boolean test(Employee t) {
				// TODO Auto-generated method stub
				return t.getName()=="chen";
			}
		});
		for(Employee employee:list){
			System.out.println(employee);
		}
		
	}
	
	//优化方式三：lambda表达式
	@Test
	public void test6(){
		List<Employee> list = filterEmployees(employees, (e)->e.getSalary()<=990);
		list.forEach(System.out::println);
	}
	
	//优化方式四：streamAPI
	@Test
	public void test7(){
		employees.stream().filter((e)->e.getSalary()<990).limit(1).forEach(System.out::println);
		System.out.println("--------------------------------------------");
		employees.stream().map(Employee::getName).forEach(System.out::println);
	}
}
