package com.htc.spring5mvc.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalHandler {

	@ExceptionHandler
	public ModelAndView handleException(java.lang.Exception ex) {
		ModelAndView mv = new ModelAndView("error", "errorMessage", ex.toString());
		return mv;
	}
}
