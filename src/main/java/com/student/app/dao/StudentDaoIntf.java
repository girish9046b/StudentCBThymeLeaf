package com.student.app.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.model.Student;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface StudentDaoIntf {

	public void UpdateStudent(Student student)  throws Exception;
	public void addStudent(Student student)  throws Exception;
	public Object getAllStudents()  throws Exception ;
	public void deleteStudent(String id)  throws Exception;
	public Student getStudent(String id)  throws Exception;
}
