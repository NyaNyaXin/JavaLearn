package com.cx.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * 一.Lambda表达式语法：Java8中引入了一个新的操作符"->"该操作符称为箭头操作符或Lambda操作符
 * 	   箭头操作符将Lambda表达式拆分成两部分
 * 左侧:Lambda表达式的参数列表
 * 右侧:Lambda表达式中所需执行的功能，即Lambda体
 * 
 * 语法格式一：无参数，无返回值
 * 		()->System.out.println("Hello Lambda")
 * 语法格式二：有一个参数，并且无返回值
 * 		(x)->System.out.println(x);
 * 语法格式三：若只有一个参数，参数的小括号可以省略不写
 * 		x->System.out.println(x);
 * 语法格式四：有两个以上的参数，并且Lambda体中有多条语句，有返回值
 * 		(x,y)->{
 *			System.out.println("Fun Inter");
 *			return Integer.compare(x, y);
 *		};
 * 语法格式五：若Lambda体中只有一条语句，return和大括号都可以省略不写
 * 		(x,y)->Integer.compare(x, y);
 * 语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器可以通过上下问推断出数据类型，这个过程叫“类型推断”
 * 		(Integer x,Integer y)->Integer.compare(x, y);
 * 
 * 上联：左右遇一括号省
 * 下联：左侧推断类型省
 * 横批：能省则省
 * 
 * 二、Lambda表达式需要"函数式接口"的支持
 * 函数式接口：接口中只有一个抽象方法的接口。可以使用 @FunctionalInterface修饰
 * 			   可以检查是否是函数式接口
 * **/
public class TestLambda2 {
	@Test
	public void test1(){
		int num =  0;//jdk 1.7以前必须是final
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
		show(new HashMap<>());//这种叫类型推断的升级，在1.8之前是不支持的
	}
	
	public void show(Map<String, Integer> map){
		
	}
	
	//需求:对一个数进行运算
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
