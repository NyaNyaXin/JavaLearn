package com.cx.java8.optional;

import java.util.Optional;

import org.junit.Test;

import com.cx.java8.lambda.Employee;
import com.cx.java8.lambda.Employee.Status;

/*
 * Optional������ĳ��÷�����
 * Optional.of(T t):����һ��Optionalʵ��
 * Optional.empty()������һ���յ�Optionalʵ��
 * Optional.ofNullable(T t):��t��Ϊnull������Optionalʵ�������򴴽���ʵ��
 * isPresent():�ж��Ƿ������ֵ
 * orElse(T t):������ö������ֵ�����ظ�ֵ�����򷵻�t
 * orElseGet(Supplier s):������ö������ֵ�����ظ�ֵ�����򷵻�s��ȡ��ֵ
 * map(Function f):�����ֵ���䴦�������ش�����Optional,���򷵻�Optional.empty()
 * flatMap(Function mapper):��map���ƣ�Ҫ�󷵻�ֵ������Optional
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

	// ��:Man's Godness's name
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
