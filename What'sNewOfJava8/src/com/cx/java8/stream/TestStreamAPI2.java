package com.cx.java8.stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import com.cx.java8.lambda.Employee;

/**
 * 一、Stream的三个步骤 1.创建Stream 2.中间操作 3.终止操作（终端操作）
 */

public class TestStreamAPI2 {
	// 中间操作
	/*
	 * 筛选与切片
	 * filter--接收Lambda，从流中排除某些元素 
	 * limit--截断流，使其元素不超过给定数量
	 * skip(n)--跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
	 * distinct--筛选，通过流所生成元素的hashCode()和equals()去除重复元素
	 * 
	 **/

	List<Employee> employees = Arrays.asList(
			new Employee("chen", 18, 999.99), new Employee("xin", 28, 939.99),
			new Employee("ding", 38, 999.99), new Employee("jian", 8, 929.99), 
			new Employee("ding", 38, 999.99), new Employee("jian", 8, 929.99), 
			new Employee("hui", 50, 999.99));

	//内部迭代：迭代操作由StreamAPI完成
	@Test
	public void test1() {
		//中间操作：不会执行任何操作
		Stream<Employee> stream = employees.stream().filter((e)->{
			System.out.println("StreamAPI的中间操作");
			return e.getAge()>35;
		});
		//终止操作：一次性执行全部内容，即“惰性求值”
		stream.forEach(System.out::println);
	}
	
	//外部迭代
	@Test
	public void test2(){
		Iterator<Employee> it= employees.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	@Test
	public void test3(){
		employees.stream().filter((e)->{
			System.out.println("短路");//&& ||
			return e.getSalary()>990;
		
		}).limit(2).forEach(System.out::println);
	}
	
	@Test
	public void test4(){
		employees.stream().filter((e)->e.getSalary()>990).skip(2).forEach(System.out::println);
	}
	
	@Test
	public void test5(){
		employees.stream().distinct().forEach(System.out::println);
	}
}
