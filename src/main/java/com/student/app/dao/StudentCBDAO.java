package com.student.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import com.couchbase.client.java.view.ViewResult;
import com.student.app.couchbase.CBOperations;
import com.student.app.model.Student;

@Repository(value="studentdaocb")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentCBDAO implements StudentDaoIntf{

	@Autowired
	CBOperations cboperations;
	
//	@Autowired
//	ConnectionManager connectionManager;


	public void UpdateStudent(Student student) throws Exception {
		cboperations.update("student_" + student.getId(), student);
	}
	
	public void addStudent(Student student) throws Exception {
		cboperations.update("student_" + student.getId(), student);
	}

	public ViewResult getAllStudents() throws Exception {
		//System.out.println(".................connectionManager.............."+connectionManager.getConnection());
		ViewResult viewResult = null;
		viewResult = cboperations.doQuery("students", "getAllDocs");
		return viewResult;
	}

	public void deleteStudent(String id) throws Exception {
		cboperations.EraseFromCB(id);
	}

	public Student getStudent(String id) throws Exception {
		Student student = null;
		student = cboperations.get(id, Student.class);
		return student;
	}

}
