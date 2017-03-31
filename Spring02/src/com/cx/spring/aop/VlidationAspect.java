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
	 * 注意：这里因为在同一个包下所以仅仅写类名就可以了，要是不再同一个包下，你懂的
	 * **/
	@Before("LogginAspect.declareJoinPointExpression()")
	public void validateArgs(JoinPoint joinpoint){
		System.out.println("validate:"+Arrays.asList(joinpoint.getArgs()));
	}
}
