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
 * Java8内置的四大核心函数式接口
 * 
 * Consumer<T>:消费型接口 void accept(T t); Supplier<T>:供给型接口 T get();
 * Function<T,R>:函数型接口 R apply(T t); Predicate<T>:断言型接口 boolean test(T t);
 */
public class TestLambda3 {

	// Predicate<T> 断言型接口
	
	@Test
	public void test4(){
		List<String> list = Arrays.asList("Hello","chen","www","lambda","ok");
		List<String> strlist = filterStr(list, (s)->s.length()>3);
		for(String str:strlist){
			System.out.println(str);
		}
	}
	//需求：将满足条件的字符串添加到集合中
	public List<String> filterStr(List<String> list,Predicate<String> pre){
		List<String> strlist = new ArrayList<>();
		for(String str:list){
			if(pre.test(str)){
				strlist.add(str);
			}
		}
		return strlist;
	}
	// Function<T,R> 函数型接口

	@Test
	public void test3() {
		System.out.println(strHandler("AAAaaaAAAA", (str) -> str.toLowerCase()));
	}

	// 需求：用于处理字符串
	public String strHandler(String str, Function<String, String> fun) {

		return fun.apply(str);
	}

	// Supplier<T> 供给型接口
	@Test
	public void test2() {
		List<Integer> nums = getNumList(10, () -> (int) (Math.random() * 100));
		for (Integer num : nums) {
			System.out.println(num);
		}
	}

	// 需求：产生一些整数，并放入到集合中
	public List<Integer> getNumList(int num, Supplier<Integer> sup) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			Integer n = sup.get();
			list.add(n);
		}
		return list;
	}

	// Consumer<T> 消费型接口

	@Test
	public void test1() {
		happy(10000, (m) -> System.out.println("Pay" + m + "Dolor"));
	}

	public void happy(double money, Consumer<Double> consumer) {
		consumer.accept(money);
	}
}
