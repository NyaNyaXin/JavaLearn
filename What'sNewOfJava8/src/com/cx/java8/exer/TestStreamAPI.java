package com.cx.java8.exer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.cx.java8.lambda.Employee;
import com.cx.java8.lambda.Employee.Status;

public class TestStreamAPI {
	List<Employee> employees = Arrays.asList(new Employee("chen", 18, 999.99, Status.FREE),
			new Employee("xin", 28, 939.99, Status.BUSY), new Employee("ding", 38, 999.99, Status.VOCATION),
			new Employee("jian", 8, 929.99, Status.FREE), new Employee("ding", 38, 999.99, Status.BUSY));
	@Test
	public void test(){
		Integer[] nums = new Integer[]{1,2,3,4,5};
		Arrays.stream(nums).map((x)->x*x).forEach(System.out::println);
	}
	
	@Test
	public void test2(){
		Optional<Integer> count = employees.stream().map((e)->1).reduce(Integer::sum);
		System.out.println(count.get());
	}
}
