package com.student.app.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.couchbase.model.ConcreteDocument;
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Marks extends ConcreteDocument {

	private int studentid;
	private int semister;
	private double english;
	private double maths;
	private double science;
	private double social;
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public int getSemister() {
		return semister;
	}
	public void setSemister(int semister) {
		this.semister = semister;
	}
	public double getEnglish() {
		return english;
	}
	public void setEnglish(double english) {
		this.english = english;
	}
	public double getMaths() {
		return maths;
	}
	public void setMaths(double maths) {
		this.maths = maths;
	}
	public double getScience() {
		return science;
	}
	public void setScience(double science) {
		this.science = science;
	}
	public double getSocial() {
		return social;
	}
	public void setSocial(double social) {
		this.social = social;
	}
	
	
}
