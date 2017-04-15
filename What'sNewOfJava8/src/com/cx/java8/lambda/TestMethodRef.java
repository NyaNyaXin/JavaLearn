package com.cx.java8.lambda;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * һ���������ã���Lambda���е������з����Ѿ�ʵ���ˣ�����ʹ�á�����Ӧ�á� (�������Ϊ����������Lambda���ʽ������һ�ֱ�����ʽ)
 * 
 * ��Ҫ�������﷨��ʽ
 *
 * 
 * ����::ʵ��������
 * 
 * ��::��̬������
 * 
 * ��::ʵ��������
 * 
 * ע�⣺ ��.Lambda���е��÷����Ĳ����б��뷵��ֵ���ͣ�Ҫ�뺯��ʽ�ӿ��г��󷽷��Ĳ����б�ͷ���ֵ���ͱ���һ��
 * ��.��Lambda���ʽ�����б��еĵ�һ��������ʵ�������ĵ����ߣ����ڶ���������ʵ�������Ĳ���ʱ������ʹ��className::methodName
 * 
 * ��������������
 * 
 * ��ʽ��
 * 
 * ClassName::new
 * 
 * ע�⣺��Ҫ���õĹ����������б�Ҫ�뺯��ʽ�ӿ��еĲ����б���һ�£�
 * 
 * ������������
 * 
 * Type::new
 **/
public class TestMethodRef {

	// ����::ʵ��������
	@Test
	public void test1() {
		Consumer<String> con = (x) -> System.out.println(x);

		PrintStream ps = System.out;
		Consumer<String> con1 = ps::println;

		Consumer<String> con2 = System.out::println;
		con2.accept("AAAAAA");
	}

	@Test
	public void test2() {
		Employee emp = new Employee();
		Supplier<String> sup1 = () -> emp.getName();
		Supplier<String> sup = emp::getName;
		System.out.println(sup.get());
	}

	// ��::��̬������
	@Test
	public void test3() {
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
		Comparator<Integer> com1 = Integer::compare;
	}

	// ��::ʵ��������
	@Test
	public void test4() {
		BiPredicate<String, String> bip = (x, y) -> x.equals(y);

		BiPredicate<String, String> bip2 = String::equals;

		boolean a = bip2.test("aaa", "aaa");
		System.out.println(a);
	}

	// ����������
	@Test
	public void test5() {
		Supplier<Employee> sup = () -> new Employee();
		Employee employee = sup.get();

		// ���������÷�ʽ
		Supplier<Employee> sup2 = Employee::new;
		System.out.println(sup2.get());
	}

	@Test
	public void test6() {
		Function<Integer, Employee> fun = (x) -> new Employee(x);
		Function<Integer, Employee> fun2 = Employee::new;
		System.out.println(fun2.apply(50));
	}

	// ��������
	@Test
	public void test7() {
		Function<Integer, String[]> fun = (x) -> new String[x];
		System.out.println(fun.apply(10).length);
		Function<Integer, String[]> fun2 = String[]::new;
		System.out.println(fun2.apply(20).length);
	}
}
