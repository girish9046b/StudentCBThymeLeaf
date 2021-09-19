package com.student.app.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.student.app.couchbase.CBSession;
import com.student.app.logging.AppLogger;
import com.student.app.model.Login;
import com.student.app.util.Util;

@Controller
public class BaseController {

	@Autowired
	public CBSession cbSession;
	@Autowired
	public Util util;
	@Autowired
	public AppLogger appLogger;
	
	public BaseController(){
		
		
	}
	
	@ModelAttribute("loginUser")
	   public Login getLoginUser(HttpSession session) throws Exception {
		
		Login login = (Login) cbSession.getSession(session, "login", Login.class);
		//System.out.println(".....333...loginUserloginUserloginUserloginUser............in login"+login.getUserName());
	     return login;
	   }
	
	@ModelAttribute("loginUserName")
	   public String getLoginSuccess(HttpSession session) throws Exception {
		
		String loginUserName = cbSession.getSession(session, "loginUserName");
		//System.err.println(".....333...loginUserNameloginUserNameloginUserNameloginUserNameloginUserName............in login"+loginUserName);
	     return loginUserName;
	   }
	
	@ModelAttribute("sessiontime")
	   public int sessionTime(HttpSession session) throws Exception {
		int sessiontime = session.getMaxInactiveInterval();
		appLogger.appInfoLog(".....333...loginUserNameloginUssessiontimesessiontimesessiontimesessiontime"+sessiontime);
		return sessiontime;
	   }
	
	@ModelAttribute("countryList")
	public java.util.Map<java.lang.String, java.lang.String> getCountryList() throws Exception {
		//System.err.println("getCountryListgetCountryListgetCountryListgetCountryList------11111");
		Map<String, String> countryList = util.getCountryList();
		// System.out.println("..................."+countryList.size());
		return countryList;
	}

	@ModelAttribute("standardList")
	public List<String> getStandardList() throws Exception {
		//System.err.println("getStandardListgetStandardListgetStandardListgetStandardList------111111");
		List<String> standardList = util.getStandardList();
		return standardList;
	}
	
	
}
