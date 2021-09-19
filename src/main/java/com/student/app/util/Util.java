package com.student.app.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.couchbase.CBSession;


@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Util {
	@Autowired
	CBSession cbSession;
	
	@Cacheable("countryList")
	public Map<String, String> getCountryList() {
		//System.err.println("getCountryListgetCountryListgetCountryListgetCountryList------222222");
	      Map<String, String> countryList = new HashMap<>();
	      countryList.put("IN", "INDIA");
	      countryList.put("US", "USA");
	      countryList.put("CA", "CANADA");
	      countryList.put("UK", "UNITED KINGDOM");
	      countryList.put("AUS", "AUSTRALIA");
	      return countryList;
	   }
	
		@Cacheable("standardList")
	   public List<String> getStandardList() {
			//System.err.println("getStandardListgetStandardListgetStandardListgetStandardList------222222");
	      List<String> standardList = new ArrayList<>();
	      standardList.add("1");
	      standardList.add("2");
	      standardList.add("3");
	      standardList.add("4");
	      return standardList;
	   }

	public void setStandardList(List<String> standardList) {
	}

	public void setCountryList(Map<String, String> countryList) {
	} 
	
	public boolean isValidLogin(HttpSession session) {
		String loginUserName = cbSession.getSession(session, "loginUserName")==null?"":cbSession.getSession(session, "loginUserName");
		
		return loginUserName.length()>0;
	
	}
	   
	   
}
