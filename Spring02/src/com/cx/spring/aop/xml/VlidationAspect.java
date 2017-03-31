package com.cx.spring.aop.xml;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;

public class VlidationAspect {
	public void validateArgs(JoinPoint joinpoint){
		System.out.println("validate:"+Arrays.asList(joinpoint.getArgs()));
	}
}
 