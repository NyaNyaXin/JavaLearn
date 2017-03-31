package com.cx.spring.aop.impl;

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
	@Before("execution(public int com.cx.spring.aop.impl.ArithmeticCaculator.*(..))")
	public void validateArgs(JoinPoint joinpoint){
		System.out.println("validate:"+Arrays.asList(joinpoint.getArgs()));
	}
}
