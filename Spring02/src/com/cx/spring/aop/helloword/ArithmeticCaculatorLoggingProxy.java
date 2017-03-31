package com.cx.spring.aop.helloword;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import com.cx.spring.aop.impl.ArithmeticCaculator;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class ArithmeticCaculatorLoggingProxy {
	
	//Ҫ����Ķ���
	private ArithmeticCaculator target;
	
	
	
	public ArithmeticCaculatorLoggingProxy(ArithmeticCaculator target) {
		this.target = target;
	}



	public ArithmeticCaculator getLoggingProxy(){
		ArithmeticCaculator proxy = null;
		//�����������һ����������������
		ClassLoader loader = target.getClass().getClassLoader();
		//�����������ͣ�����������Щ����
		Class[] interfaces = new Class[]{ArithmeticCaculator.class};
		//�����ô���������еķ���ʱ����ִ�еĴ��� 
		InvocationHandler h =new InvocationHandler() {
			/**
			 * proxy:���ڷ��ص��Ǹ��������һ������£���invoke�����ж���ʹ�øö���
			 * method�����ڱ����õķ���
			 * args�����÷���ʱ����Ĳ���
			 * */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				String methodName = method.getName();
				//��־
				System.out.println("The Method "+methodName+"begins with"+Arrays.asList(args));
				//ִ�з���
				Object result = method.invoke(target, args);
				//��־
				System.out.println("The method"+methodName+"Ends with"+result);
				return result;
			}
		};
		proxy = (ArithmeticCaculator) Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
	}
	
	
}
