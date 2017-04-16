package com.cx.java8.stream;
/**
 * ��ֹ����
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
	 * ������ƥ�䣺 allMatch--����Ƿ�ƥ������Ԫ�� anyMatch--����Ƿ�����ƥ��һ��Ԫ��
	 * noneMatch--����Ƿ�û��ƥ������Ԫ�� findFirst--���ص�һ��Ԫ�� findAny--���ص�ǰ���е�����Ԫ��
	 * count--��������Ԫ�ص��ܸ��� max--�����������ֵ min--����������Сֵ
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
	 * ��Լ reduce(T
	 * identity,BinaryOperator)/reduce(BinaryOperator)--���Խ�����Ԫ�ط�������������õ�һ��ֵ
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
	 * �ռ��� collect--����ת��Ϊ������ʽ������һ��Collector�ӿڵ�ʵ�֣����ڸ�Stream��Ԫ�������ܵķ���
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
		// ����
		Long count = employees.stream().collect(Collectors.counting());
		System.out.println(count);

		System.out.println("-------------------------------------------");
		// ƽ��ֵ
		Double avg = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
		System.out.println(avg);

		System.out.println("-------------------------------------------");
		// �ܺ�
		Double sum = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
		System.out.println(sum);
		// ���ֵ
		System.out.println("-------------------------------------------");
		Optional<Employee> max = employees.stream()
				.collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
		System.out.println(max.get());
		// ��Сֵ
		System.out.println("-------------------------------------------");
		Optional<Double> min = employees.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
		System.out.println(min.get());

	}

	@Test
	public void test5() {
		// ����
		Map<Status, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(Employee::getStatus));
		System.out.println(map);
	}

	@Test
	public void test6() {
		// �༶����
	 Map<Status, Map<String,List<Employee>>> map =  employees.stream()
				.collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
					if (((Employee)e).getAge() <= 35) {
						return "����";
					} else if (((Employee)e).getAge() <= 50) {
						return "����";
					} else {
						return "����";
					}
				})));
	 System.out.println(map);
	}
	@Test
	public void test7(){
		//����
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
