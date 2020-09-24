package com.htc.spring5mvc.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.htc.spring5mvc.beans.Product;

@Controller
public class WelcomeController {
	Logger logger =  Logger.getLogger(WelcomeController.class);
	@RequestMapping(method=RequestMethod.GET,value="/index")
	public String index() {
		logger.info("Calling methods");
		return "index";
	}
	
	//@RequestMapping(method=RequestMethod.GET,value="/showProduct")
	@GetMapping(value="/showProduct")
	public ModelAndView showProduct(){
		logger.info("Calling methods");
		Product p = new Product("PP01", "PEN", 50.0, 100,"STAT");			
		ModelAndView mv = new ModelAndView("showproduct", "prod", p);
		return mv;
	}
	
	
}
