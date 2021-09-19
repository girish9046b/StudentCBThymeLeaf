package com.student.app.rest.restapi;

import org.springframework.web.servlet.ModelAndView;

public class Response {
	  private String status;
	  private ModelAndView data;
	  private String key;
	  
	  public Response(){
	    
	  }
	  
	  public Response(String status, ModelAndView data){
	    this.status = status;
	    this.data = data;
	  }
	  public Response(String status, String key){
		    this.status = status;
		    this.key = key;
		  }
	 
	  public String getStatus() {
	    return status;
	  }
	 
	  public void setStatus(String status) {
	    this.status = status;
	  }
	 
	  public ModelAndView getData() {
	    return data;
	  }
	 
	  public void setData(ModelAndView data) {
	    this.data = data;
	  }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	  
	  
	}