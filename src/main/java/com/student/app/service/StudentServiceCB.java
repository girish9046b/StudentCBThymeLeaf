package com.student.app.service;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.couchbase.client.java.view.ViewResult;
import com.couchbase.client.java.view.ViewRow;
import com.google.gson.Gson;
import com.student.app.dao.StudentDaoIntf;
import com.student.app.model.Student;
import com.student.app.util.Messages;

@Service("studentServicecb")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentServiceCB implements StudentServiceIntf{

	@Autowired
	@Qualifier("studentdaocb")
	StudentDaoIntf studentdao;
	@Autowired
	Gson gson;
	@Autowired
	Messages messages;
	@Autowired
	Student studentP;
	
	public ArrayList<Student> getAllStudents() throws Exception {
		System.err.println("loadStudentListloadStudentListloadStudentList-----222");
		ArrayList<Student> studentslist = new ArrayList<>();
		ViewResult result = (ViewResult) studentdao.getAllStudents();
		for (ViewRow row : result) {
			Student doc = null;
			try {
				doc = gson.fromJson((String) row.value().toString(), Student.class);
				studentslist.add(doc);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Collections.sort(studentslist, Student.stuidComparator);
		}
		return studentslist;
	}
	

	public void deleteStudent(String id) throws Exception {
		studentdao.deleteStudent(studentP.getPrefix()+id);

	}

	public Student getStudent(String id) throws Exception {
		Student student = studentdao.getStudent(studentP.getPrefix()+id);
		return student;
	}
	
	public void UpdateStudent(Student student) throws Exception {
		studentdao.UpdateStudent(student);
	}
	
	public void addStudent(Student student) throws Exception {
		studentdao.UpdateStudent(student);
	}
	
}
