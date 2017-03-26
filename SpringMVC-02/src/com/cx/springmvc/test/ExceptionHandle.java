package com.cx.springmvc.test;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandle {
	@ExceptionHandler({ArithmeticException.class})
	public ModelAndView arithmeticException(Exception ex) {
		System.out.println("------------>≥ˆ“Ï≥£¡À£∫" + ex);
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", ex);
		return mv;
	}
}
