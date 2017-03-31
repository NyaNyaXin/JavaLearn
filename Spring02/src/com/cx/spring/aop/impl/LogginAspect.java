package com.cx.spring.aop.impl;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//�����������Ϊһ�����棺��Ҫ�Ѹ�����뵽IOC�����У�������Ϊһ������
@Component
@Aspect
public class LogginAspect {
	//�����÷�����һ��ǰ��֪ͨ:��Ŀ�귽����ʼ֮ǰִ��
	@Before("execution(public int com.cx.spring.aop.impl.ArithmeticCaculator.*(int, int))")
	public void beforeMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("The Method"+methodName+" Begins"+" begin with"+args);
	}
	
	//����֪ͨ����Ŀ�귽��ִ�к������Ƿ����쳣����ִ�е�֪ͨ
	//�ں���֪ͨ�л����ܷ���Ŀ�귽����ִ�н��
	@After("execution(public int com.cx.spring.aop.impl.ArithmeticCaculator.*(int, int))")
	public void afterMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("The Method"+methodName+" Ends");
	}
	//�ڷ�������������ִ�еĴ���
	//����֪ͨ�����Է��ʵ������ķ���ֵ
	@AfterReturning(value="execution(public int com.cx.spring.aop.impl.ArithmeticCaculator.*(..))",returning="result")
	public void afterReturnning(JoinPoint joinPoint,Object result){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The Method"+methodName+" Ends Normally Ends with"+result);
	}
	//�쳣֪ͨ��
	/**
	 * ��Ŀ�귽�������쳣ʱ��ִ�еĴ���
	 * ���Է��ʵ��쳣�����ҿ���ָ���ڳ����ض��쳣ʱ��ִ��֪ͨ����
	 * **/
	@AfterThrowing(value="execution(public int com.cx.spring.aop.impl.ArithmeticCaculator.*(..))",throwing="ex")
	public void afterThrowing(JoinPoint joinPoint,Exception ex){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The Method"+methodName+" occurs Exception :"+ex);
	}
	/***
	 * ����֪ͨ��ҪЯ��ProceedingJoinPoint���͵Ĳ���
	 * ����֪ͨ�����ڶ�̬�����ȫ���̣�ProceedingJoinPoint���͵Ĳ������Ծ����Ƿ�ִ��Ŀ�귽��
	 * �һ���֪ͨ�����з���ֵ������ֵ��ΪĿ�귽���ķ���ֵ
	 * */
	@Around("execution(public int com.cx.spring.aop.impl.ArithmeticCaculator.*(..))")
	public Object aroundMethod(ProceedingJoinPoint pjd){
		
		Object result = null;
		String methodName = pjd.getSignature().getName();
		//ִ��Ŀ�귽��
		try {
			//ǰ��֪ͨ
			System.out.println("The method "+ methodName+"begins with"+Arrays.asList(pjd.getArgs()));
			result = pjd.proceed();
			//����֪ͨ
			System.out.println("The method Endswith"+result);
		} catch (Throwable e) {
			//�쳣֪ͨ
			System.out.println("The methodoccursexception"+e+methodName);
			throw new RuntimeException(e);
		}
		//����֪ͨ
		System.out.println("Ends"+methodName);
		return result;
	}
}
