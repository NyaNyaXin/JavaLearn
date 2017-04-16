package com.cx.java8.stream;
/**
 * 终止操作
 * */

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

		Optional<Employee> op2 = employees.parallelStream().filter((e) -> e.getStatus().equals(Status.FREE)).findAny();
		System.out.println(op2.get());

		System.out.println(employees.stream().count());

		Optional<Employee> op3 = employees.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
		System.out.println(op3.get());

		Optional<Double> op4 = employees.stream().map(Employee::getSalary).min(Double::compare);
		System.out.println(op4.get());
	}

	/**
	 * 规约 reduce(T
	 * identity,BinaryOperator)/reduce(BinaryOperator)--可以将流中元素反复结合起来，得到一个值
	 */
	@Test
	public void test2() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Integer sum = list.stream().reduce(0, (x, y) -> x + y);
		System.out.println(sum);
		System.out.println("-------------------------------------------");
		Optional<Double> op = employees.stream().map(Employee::getSalary).reduce(Double::sum);
		System.out.println(op.get());
	}

	/*
	 * 收集： collect--将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
	 **/
	@Test
	public void test3() {
		List<String> list = employees.stream().map(Employee::getName).collect(Collectors.toList());
		list.forEach(System.out::println);

		System.out.println("------------------------------------------");
		Set<String> set = employees.stream().map(Employee::getName).collect(Collectors.toSet());
		set.forEach(System.out::println);

		System.out.println("-------------------------------------------");
		HashSet<String> has = employees.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
		has.forEach(System.out::println);
	}

	@Test
	public void test4() {
		// 总数
		Long count = employees.stream().collect(Collectors.counting());
		System.out.println(count);

		System.out.println("-------------------------------------------");
		// 平均值
		Double avg = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
		System.out.println(avg);

		System.out.println("-------------------------------------------");
		// 总和
		Double sum = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
		System.out.println(sum);
		// 最大值
		System.out.println("-------------------------------------------");
		Optional<Employee> max = employees.stream()
				.collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
		System.out.println(max.get());
		// 最小值
		System.out.println("-------------------------------------------");
		Optional<Double> min = employees.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
		System.out.println(min.get());

	}

	@Test
	public void test5() {
		// 分组
		Map<Status, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(Employee::getStatus));
		System.out.println(map);
	}

	@Test
	public void test6() {
		// 多级分组
	 Map<Status, Map<String,List<Employee>>> map =  employees.stream()
				.collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
					if (((Employee)e).getAge() <= 35) {
						return "青年";
					} else if (((Employee)e).getAge() <= 50) {
						return "中年";
					} else {
						return "老年";
					}
				})));
	 System.out.println(map);
	}
	@Test
	public void test7(){
		//分区
		Map<Boolean, List<Employee>> map = employees.stream().collect(Collectors.partitioningBy((e)->e.getSalary()>950));
		System.out.println(map);
	}
	
	@Test
	public void test8(){
		DoubleSummaryStatistics dss = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println(dss.getSum());
		
	}
	
	@Test
	public void test9(){
		String s = employees.stream().map(Employee::getName).collect(Collectors.joining(",","===","==="));
		System.out.println(s);
	}
}
