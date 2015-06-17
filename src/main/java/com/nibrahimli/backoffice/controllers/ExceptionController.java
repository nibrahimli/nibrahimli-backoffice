package com.nibrahimli.backoffice.controllers;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHhandling(Exception e){
	  ModelAndView mav = new ModelAndView("/error/error");
	  mav.addObject("name", e.getClass().getName());
      return mav;
	}
}
