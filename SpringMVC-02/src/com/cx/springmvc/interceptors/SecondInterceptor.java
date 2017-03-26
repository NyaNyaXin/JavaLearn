package com.cx.springmvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SecondInterceptor implements HandlerInterceptor{

	
	/**�÷�����Ŀ�귽��֮ǰ������
	 *������ֵΪtrue��������ú�������������Ŀ�귽��
	 *������ֵΪfalse�򲻻��ٵ��ú�������������Ŀ�귽��
	 *
	 *���Կ�����Ȩ�ޣ���־�������
	 **/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("[SecondInterceptor preHandle]");
		return true;
	}
	/**
	 * �÷����ڵ���Ŀ�귽��֮�󣬵�����Ⱦ֮ǰ������
	 * ���Զ��������е���ͼ�����������޸�
	 * */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("[SecondInterceptor postHandle]");
		
	}
	/**
	 * �÷�������Ⱦ��ͼ֮�󱻵���
	 * 
	 * �ͷ���Դ
	 * */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("[SecondInterceptor afterCompletion]");
		
	}
	
}
