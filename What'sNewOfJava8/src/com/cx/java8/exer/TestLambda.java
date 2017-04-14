package com.cx.java8.exer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.cx.java8.Employee;

public class TestLambda {

	List<Employee> employees = Arrays.asList(new Employee("chen", 18, 999.99), new Employee("xin", 28, 939.99),
			new Employee("ding", 38, 999.99), new Employee("jian", 8, 929.99), new Employee("hui", 50, 999.99));

	@Test
	public void test1() {
		Collections.sort(employees, (e1, e2) -> {
			if (e1.getAge() == e2.getAge()) {
				return e1.getName().compareTo(e2.getName());
			} else {
				return Integer.compare(e1.getAge(), e2.getAge());
			}
		});

		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}

	@Test
	public void test2() {
		String str = "aaaaaa";
		String uppercase = strHandler(str, (x) -> x.toUpperCase());
		System.out.println(str+"*********"+uppercase);
	}
	@Test
	public void test3(){
		op(100L,200L,(l1,l2)->l1+l2);
		op(100L,200L,(l1,l2)->l1*l2);
	}

	// 需求：用于处理字符串的方法
	public String strHandler(String str, MyFunction mf) {
		return mf.getValue(str);
	}
	
	//需求，处理两个long型数据
	public void op(Long l1,Long l2,MyFunction2<Long,Long> mf2){
		System.out.println(mf2.getValue(l1, l2));
	}
}
