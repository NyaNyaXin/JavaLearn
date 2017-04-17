package com.cx.java8.optional;

import java.util.Optional;

import org.junit.Test;

import com.cx.java8.lambda.Employee;
import com.cx.java8.lambda.Employee.Status;

/*
 * Optional容器类的常用方法：
 * Optional.of(T t):创建一个Optional实例
 * Optional.empty()：创建一个空的Optional实例
 * Optional.ofNullable(T t):若t不为null，创建Optional实例，否则创建空实例
 * isPresent():判断是否包含空值
 * orElse(T t):如果调用对象包含值，返回该值，否则返回t
 * orElseGet(Supplier s):如果调用对象包含值，返回该值，否则返回s获取的值
 * map(Function f):如果有值对其处理，并返回处理后的Optional,否则返回Optional.empty()
 * flatMap(Function mapper):与map类似，要求返回值必须是Optional
 * **/
public class TestOptional {
	@Test
	public void test1() {
		Optional<Employee> op = Optional.of(new Employee());
		// Optional<Employee> op = Optional.of(null);
		System.out.println(op.get());
	}

	@Test
	public void test2() {
		Optional<Employee> op = Optional.empty();
		System.out.println(op.get());
	}

	@Test
	public void test3() {
		// Optional<Employee> op = Optional.ofNullable(new Employee());
		Optional<Employee> op = Optional.ofNullable(null);
		// if(op.isPresent()){
		// System.out.println(op.get());
		// }else{
		//
		// }

		// Employee emp = op.orElse(new
		// Employee("zhansan",18,1999.22,Status.BUSY));
		// System.out.println(emp);

		Employee emp = op.orElseGet(() -> new Employee("zhansan", 18, 1999.22, Status.BUSY));
		System.out.println(emp);
	}

	@Test
	public void test4() {
		Optional<Employee> op = Optional.ofNullable(new Employee("zhansan", 18, 1999.22, Status.BUSY));
		// Optional<String> str = op.map((e)->e.getName());
		// System.out.println(str.get());
		Optional<String> str = op.flatMap((e) -> Optional.of(e.getName()));
		System.out.println(str.get());
	}

	// 例:Man's Godness's name
	public String getGodnessName(Man man) {
		if (man != null) {
			Godness godness = man.getGodness();
			if (godness != null) {
				return godness.getName();
			}
		}
		return "aaa";

	}
	
	public String getGodnessName2(Optional<NewMan> man){
		return man.orElse(new NewMan()).getGodness().orElse(new Godness("aaa")).getName();
	}
	

	@Test
	public void test5() {
//		Man man = new Man();
//		String n = getGodnessName(man);
		Godness god = new Godness("bbb");
		Optional<Godness> gn = Optional.ofNullable(god);
		Optional<NewMan> op = Optional.ofNullable(new NewMan(gn));
		String str = getGodnessName2(op);
		System.out.println(str);
	}
}
