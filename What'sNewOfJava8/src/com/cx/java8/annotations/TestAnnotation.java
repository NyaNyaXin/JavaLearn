package com.cx.java8.annotations;

import java.lang.reflect.Method;

import org.junit.Test;

//�ظ�ע��������ע��

public class TestAnnotation {
	
	//checker framework
	private /*@NonNull*/ Object obj = null;
	
	@Test
	public void test1() throws Exception{
		Class<TestAnnotation> clazz = TestAnnotation.class;
		Method m1 = clazz.getMethod("show");
		MyAnnotation[] ma = m1.getAnnotationsByType(MyAnnotation.class);
		for(MyAnnotation m:ma){
			System.out.println(m.value());
		}
	}
	@MyAnnotation("hello")
	@MyAnnotation("world")
	public void show(@MyAnnotation("abc") String str){
		
	}
}
