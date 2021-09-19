package com.student.app.dao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import com.couchbase.client.java.view.ViewResult;
import com.student.app.couchbase.CBOperations;
import com.student.app.model.Login;

@Repository
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginDAO {
	@Autowired
	CBOperations cboperations;
	

//	public ViewResult isValidUser(Login login) throws Exception{
//		ViewResult viewResult  = null;
//			 viewResult = cboperations.doQuery("users", "getAllDocs");
//		return viewResult;
//	}
	
	public ViewResult isValidUser(Login login) throws Exception{
		ViewResult viewResult  = null;
		HashMap<String,String> hmKeys= new HashMap<>();
		hmKeys.put("jsonType","sas_user");
		hmKeys.put("lastName","m");
			 viewResult = cboperations.doQueryDynamicView(hmKeys);
		return viewResult;
	}

}
