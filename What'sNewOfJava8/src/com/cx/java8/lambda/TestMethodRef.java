package com.cx.java8.lambda;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * 一：方法引用：若Lambda体中的内容有方法已经实现了，可以使用“方法应用” (可以理解为方法引用是Lambda表达式的另外一种表现形式)
 * 
 * 主要有三种语法格式
 *
 * 
 * 对象::实例方法名
 * 
 * 类::静态方法名
 * 
 * 类::实例方法名
 * 
 * 注意： Ⅰ.Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的参数列表和返回值类型保持一致
 * Ⅱ.若Lambda表达式参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用className::methodName
 * 
 * 二、构造器引用
 * 
 * 格式：
 * 
 * ClassName::new
 * 
 * 注意：需要调用的构造器参数列表要与函数式接口中的参数列表保持一致！
 * 
 * 三、数组引用
 * 
 * Type::new
 **/
public class TestMethodRef {

	// 对象::实例方法名
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

	// 类::静态方法名
	@Test
	public void test3() {
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
		Comparator<Integer> com1 = Integer::compare;
	}

	// 类::实例方法名
	@Test
	public void test4() {
		BiPredicate<String, String> bip = (x, y) -> x.equals(y);

		BiPredicate<String, String> bip2 = String::equals;

		boolean a = bip2.test("aaa", "aaa");
		System.out.println(a);
	}

	// 构造器引用
	@Test
	public void test5() {
		Supplier<Employee> sup = () -> new Employee();
		Employee employee = sup.get();

		// 构造器引用方式
		Supplier<Employee> sup2 = Employee::new;
		System.out.println(sup2.get());
	}

	@Test
	public void test6() {
		Function<Integer, Employee> fun = (x) -> new Employee(x);
		Function<Integer, Employee> fun2 = Employee::new;
		System.out.println(fun2.apply(50));
	}

	// 数组引用
	@Test
	public void test7() {
		Function<Integer, String[]> fun = (x) -> new String[x];
		System.out.println(fun.apply(10).length);
		Function<Integer, String[]> fun2 = String[]::new;
		System.out.println(fun2.apply(20).length);
	}
}
