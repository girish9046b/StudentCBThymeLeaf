package com.student.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.couchbase.client.java.view.ViewResult;
import com.couchbase.client.java.view.ViewRow;
import com.google.gson.Gson;
import com.student.app.dao.LoginDAO;
import com.student.app.model.Login;
import com.student.app.util.Messages;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)//CHECK AHEN TO USE DIFFETEENT SCOPES 
public class LoginService {
	
	@Autowired
	LoginDAO logindao;
	@Autowired
	Gson gson;
	@Autowired
	Messages messages;
	
	public Login isValidUser(Login login) throws Exception{
		Login loginDoc=null;
		ViewResult viewResult = logindao.isValidUser(login);
		int count=0;
		for (ViewRow row : viewResult) {
			loginDoc = gson.fromJson((String) row.value().toString(), Login.class);
			System.out.println(".......loginDocloginDocloginDoc............"+loginDoc);
			if (loginDoc != null && loginDoc.getUserName().equals(login.getUserName())
					&& loginDoc.getPassword().equals(login.getPassword())) {
				count++;
				break;
			}
		}
		
		if(count==1) {
			return loginDoc;
		}
		else {
			login.setErrmsg(messages.get("login.fail"));
			return login;
		}
	}
}
