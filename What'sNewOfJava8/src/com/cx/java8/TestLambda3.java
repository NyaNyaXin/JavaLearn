package com.cx.java8;

import java.io.FilterInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * Java8���õ��Ĵ���ĺ���ʽ�ӿ�
 * 
 * Consumer<T>:�����ͽӿ� void accept(T t); Supplier<T>:�����ͽӿ� T get();
 * Function<T,R>:�����ͽӿ� R apply(T t); Predicate<T>:�����ͽӿ� boolean test(T t);
 */
public class TestLambda3 {

	// Predicate<T> �����ͽӿ�
	
	@Test
	public void test4(){
		List<String> list = Arrays.asList("Hello","chen","www","lambda","ok");
		List<String> strlist = filterStr(list, (s)->s.length()>3);
		for(String str:strlist){
			System.out.println(str);
		}
	}
	//���󣺽������������ַ�����ӵ�������
	public List<String> filterStr(List<String> list,Predicate<String> pre){
		List<String> strlist = new ArrayList<>();
		for(String str:list){
			if(pre.test(str)){
				strlist.add(str);
			}
		}
		return strlist;
	}
	// Function<T,R> �����ͽӿ�

	@Test
	public void test3() {
		System.out.println(strHandler("AAAaaaAAAA", (str) -> str.toLowerCase()));
	}

	// �������ڴ����ַ���
	public String strHandler(String str, Function<String, String> fun) {

		return fun.apply(str);
	}

	// Supplier<T> �����ͽӿ�
	@Test
	public void test2() {
		List<Integer> nums = getNumList(10, () -> (int) (Math.random() * 100));
		for (Integer num : nums) {
			System.out.println(num);
		}
	}

	// ���󣺲���һЩ�����������뵽������
	public List<Integer> getNumList(int num, Supplier<Integer> sup) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			Integer n = sup.get();
			list.add(n);
		}
		return list;
	}

	// Consumer<T> �����ͽӿ�

	@Test
	public void test1() {
		happy(10000, (m) -> System.out.println("Pay" + m + "Dolor"));
	}

	public void happy(double money, Consumer<Double> consumer) {
		consumer.accept(money);
	}
}
