package com.student.app.xss;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

//@WebFilter("/*")
@Configuration
@Order(1)
public class InterceptFilter implements Filter {

	private FilterConfig config = null;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//System.out.println("inside InterceptFilter");
		RequestWrapper reqwrapper = new RequestWrapper((HttpServletRequest) request);
		StripResponseWrapper reswrapper = new StripResponseWrapper((HttpServletResponse) response);
		chain.doFilter(reqwrapper, reswrapper);
	}

	
}
