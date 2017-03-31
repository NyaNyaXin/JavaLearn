package com.cx.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
@Aspect
@Order(1)
public class VlidationAspect {
	/**
	 * ע�⣺������Ϊ��ͬһ���������Խ���д�����Ϳ����ˣ�Ҫ�ǲ���ͬһ�����£��㶮��
	 * **/
	@Before("LogginAspect.declareJoinPointExpression()")
	public void validateArgs(JoinPoint joinpoint){
		System.out.println("validate:"+Arrays.asList(joinpoint.getArgs()));
	}
}
