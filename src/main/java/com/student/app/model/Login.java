/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.app.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.couchbase.model.ConcreteDocument;

/**
 *
 * @author Namratha
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Login extends ConcreteDocument {

	@NotEmpty(message = "{userName.required}") // @Size(min=2, max=30)
	private String userName="";
	@NotEmpty(message = "{password.required}") // @Min(5)
	private String password="";

	private String lastName = "";
	private String email = "";
	private String errmsg = "";

	public Login(){
		setJsonType("app_student");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	@Override
	public String toString() {
		return "Login [userName=" + userName + ", password=" + password + ", lastName=" + lastName + ", email=" + email
				+ ", errmsg=" + errmsg + "]";
	}

}
