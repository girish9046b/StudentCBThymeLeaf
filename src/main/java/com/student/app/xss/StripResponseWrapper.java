package com.student.app.xss;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


public class StripResponseWrapper  extends HttpServletResponseWrapper {
	
	public StripResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	public String encodeUrl(String url) {
		return new ValidCheck().fixSpecialHTMLCharacters(url);
	}

	public String encodeURL(String url) {
		return new ValidCheck().fixSpecialHTMLCharacters(url);
	}



}
