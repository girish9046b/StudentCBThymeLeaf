package com.student.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.student.app.dao.StudentDaoIntf;
import com.student.app.model.Student;
import com.student.app.util.Messages;

@Service("studentServicemysql")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentServiceMysql implements StudentServiceIntf{

	@Autowired
	@Qualifier("studentdaomysql")//studentdaomysqlprocedure //studentdaomysql
	StudentDaoIntf studentdao;
	@Autowired
	Gson gson;
	@Autowired
	Messages messages;
	
	//code for the mysql transactions
	
	public void UpdateStudent(Student student) throws Exception {
		studentdao.UpdateStudent(student);
	}
	
	public void addStudent(Student student) throws Exception {
		studentdao.addStudent(student);
	}
	
	public void deleteStudent(String id) throws Exception {
		studentdao.deleteStudent(id);

	}
	public ArrayList<Student> getAllStudents() throws Exception {
		System.err.println("getAllStudentsmysqlgetAllStudentsmysqlgetAllStudentsmysql-----222");
		ArrayList<Student> studentslist = (ArrayList<Student>) studentdao.getAllStudents();
		return studentslist;
	}
	
	public Student getStudent(String id) throws Exception {
		Student student = studentdao.getStudent(id);
		return student;
	}
	
}
