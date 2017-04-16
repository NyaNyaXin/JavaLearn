package com.cx.java8.stream;
/**
 * 终止操作
 * */

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.cx.java8.lambda.Employee;
import com.cx.java8.lambda.Employee.Status;

public class TestStreamAPI3 {

	List<Employee> employees = Arrays.asList(new Employee("chen", 18, 999.99, Status.FREE),
			new Employee("xin", 28, 939.99, Status.BUSY), new Employee("ding", 38, 999.99, Status.VOCATION),
			new Employee("jian", 8, 929.99, Status.FREE), new Employee("ding", 38, 999.99, Status.BUSY));

	/*
	 * 查找与匹配： allMatch--检查是否匹配所有元素 anyMatch--检查是否至少匹配一个元素
	 * noneMatch--检查是否没有匹配所有元素 findFirst--返回第一个元素 findAny--返回当前流中的任意元素
	 * count--返回流中元素的总个数 max--返回流中最大值 min--返回流中最小值
	 * 
	 */
	@Test
	public void test1() {
		Boolean b = employees.stream().allMatch((e) -> e.getStatus().equals(Status.BUSY));
		System.out.println(b);

		Boolean b2 = employees.stream().anyMatch((e) -> e.getStatus().equals(Status.BUSY));
		System.out.println(b2);

		Boolean b3 = employees.stream().noneMatch((e) -> e.getStatus().equals(Status.BUSY));
		System.out.println(b3);

		Optional<Employee> op = employees.stream().sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
				.findFirst();
		System.out.println(op.get());
		
		Optional<Employee> op2 = employees.parallelStream().filter((e)->e.getStatus().equals(Status.FREE)).findAny();
		System.out.println(op2.get());
		
		System.out.println(employees.stream().count());
		
		Optional<Employee> op3 = employees.stream().max((e1,e2)->Double.compare(e1.getSalary(), e2.getSalary()));
		System.out.println(op3.get());
		
		Optional<Double> op4 = employees.stream().map(Employee::getSalary).min(Double::compare);
		System.out.println(op4.get());
	}
}
