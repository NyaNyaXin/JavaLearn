package com.cx.springmvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SecondInterceptor implements HandlerInterceptor{

	
	/**该方法在目标方法之前被调用
	 *若返回值为true则继续调用后续的拦截器和目标方法
	 *若返回值为false则不会再调用后续的拦截器和目标方法
	 *
	 *可以考虑做权限，日志，事务等
	 **/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("[SecondInterceptor preHandle]");
		return true;
	}
	/**
	 * 该方法在调用目标方法之后，但在渲染之前被调用
	 * 可以对请求域中的视图或属性做出修改
	 * */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("[SecondInterceptor postHandle]");
		
	}
	/**
	 * 该方法在渲染视图之后被调用
	 * 
	 * 释放资源
	 * */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("[SecondInterceptor afterCompletion]");
		
	}
	
}
