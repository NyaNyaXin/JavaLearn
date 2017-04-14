package com.cx.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * һ.Lambda���ʽ�﷨��Java8��������һ���µĲ�����"->"�ò�������Ϊ��ͷ��������Lambda������
 * 	   ��ͷ��������Lambda���ʽ��ֳ�������
 * ���:Lambda���ʽ�Ĳ����б�
 * �Ҳ�:Lambda���ʽ������ִ�еĹ��ܣ���Lambda��
 * 
 * �﷨��ʽһ���޲������޷���ֵ
 * 		()->System.out.println("Hello Lambda")
 * �﷨��ʽ������һ�������������޷���ֵ
 * 		(x)->System.out.println(x);
 * �﷨��ʽ������ֻ��һ��������������С���ſ���ʡ�Բ�д
 * 		x->System.out.println(x);
 * �﷨��ʽ�ģ����������ϵĲ���������Lambda�����ж�����䣬�з���ֵ
 * 		(x,y)->{
 *			System.out.println("Fun Inter");
 *			return Integer.compare(x, y);
 *		};
 * �﷨��ʽ�壺��Lambda����ֻ��һ����䣬return�ʹ����Ŷ�����ʡ�Բ�д
 * 		(x,y)->Integer.compare(x, y);
 * �﷨��ʽ����Lambda���ʽ�Ĳ����б���������Ϳ���ʡ�Բ�д����ΪJVM����������ͨ���������ƶϳ��������ͣ�������̽С������ƶϡ�
 * 		(Integer x,Integer y)->Integer.compare(x, y);
 * 
 * ������������һ����ʡ
 * ����������ƶ�����ʡ
 * ��������ʡ��ʡ
 * 
 * ����Lambda���ʽ��Ҫ"����ʽ�ӿ�"��֧��
 * ����ʽ�ӿڣ��ӿ���ֻ��һ�����󷽷��Ľӿڡ�����ʹ�� @FunctionalInterface����
 * 			   ���Լ���Ƿ��Ǻ���ʽ�ӿ�
 * **/
public class TestLambda2 {
	@Test
	public void test1(){
		int num =  0;//jdk 1.7��ǰ������final
		Runnable r =new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Hello World");
			}
		};
		r.run();
		System.out.println("--------------------------");
		Runnable r1 = ()->System.out.println("Hello Lambda");
		r1.run();
	}
	@Test
	public void test2(){
		Consumer<String> con = (x)->System.out.println(x);
		con.accept("WeiWU");
		Consumer<String> con2 = x->System.out.println(x);
		con2.accept("WeiWU");
	}
	
	@Test
	public void test3(){
		Comparator<Integer> com = (x,y)->{
			System.out.println("Fun Inter");
			return Integer.compare(x, y);
		};
		System.out.println(com.compare(3, 2));
	}
	
	@Test
	public void test4(){
		Comparator<Integer> com = (x,y)->Integer.compare(x, y);
		System.out.println(com.compare(3, 2));
		Comparator<Integer> com2 = (Integer x,Integer y)->Integer.compare(x, y);
		System.out.println(com2.compare(3, 2));
	}
	
	@Test
	public void test5(){
//		String[] strs ;
//		strs = {"aaa","bbb","ccc"};
		
		List<String> list = new ArrayList<>();
		show(new HashMap<>());//���ֽ������ƶϵ���������1.8֮ǰ�ǲ�֧�ֵ�
	}
	
	public void show(Map<String, Integer> map){
		
	}
	
	//����:��һ������������
	@Test
	public void test6(){
		Integer a = operation(100, (Integer x)->x*x);
		System.out.println(a);
		Integer aa = operation(200, (Integer x)->x+200);
		System.out.println(aa);
	}
	
	public Integer operation(Integer num,MyFunction mf){
		return mf.getValue(num);
	}
}
