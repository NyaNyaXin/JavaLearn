package com.cx.java8.stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import com.cx.java8.lambda.Employee;

/**
 * һ��Stream���������� 1.����Stream 2.�м���� 3.��ֹ�������ն˲�����
 */

public class TestStreamAPI2 {
	// �м����
	/*
	 * ɸѡ����Ƭ
	 * filter--����Lambda���������ų�ĳЩԪ�� 
	 * limit--�ض�����ʹ��Ԫ�ز�������������
	 * skip(n)--����Ԫ�أ�����һ���ӵ���ǰn��Ԫ�ص�����������Ԫ�ز���n�����򷵻�һ����������limit(n)����
	 * distinct--ɸѡ��ͨ����������Ԫ�ص�hashCode()��equals()ȥ���ظ�Ԫ��
	 * 
	 **/

	List<Employee> employees = Arrays.asList(
			new Employee("chen", 18, 999.99), new Employee("xin", 28, 939.99),
			new Employee("ding", 38, 999.99), new Employee("jian", 8, 929.99), 
			new Employee("ding", 38, 999.99), new Employee("jian", 8, 929.99), 
			new Employee("hui", 50, 999.99));

	//�ڲ�����������������StreamAPI���
	@Test
	public void test1() {
		//�м����������ִ���κβ���
		Stream<Employee> stream = employees.stream().filter((e)->{
			System.out.println("StreamAPI���м����");
			return e.getAge()>35;
		});
		//��ֹ������һ����ִ��ȫ�����ݣ�����������ֵ��
		stream.forEach(System.out::println);
	}
	
	//�ⲿ����
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
			System.out.println("��·");//&& ||
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