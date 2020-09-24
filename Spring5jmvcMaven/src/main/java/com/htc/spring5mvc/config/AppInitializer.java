package com.htc.spring5mvc.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		
		System.out.println("Starting application");
		
		//Load the application context
		AnnotationConfigWebApplicationContext wcontext = new AnnotationConfigWebApplicationContext();
		wcontext.register(WebAppConfig.class);
		
		//Create and register Dispatcher servlet
		
		DispatcherServlet servlet = new DispatcherServlet(wcontext);
		ServletRegistration.Dynamic registration = context.addServlet("app", servlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
		registration.addMapping("/resources");
	}

}
