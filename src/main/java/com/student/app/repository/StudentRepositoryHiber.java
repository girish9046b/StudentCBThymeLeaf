package com.student.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.student.app.model.Student;

@Repository
	public interface StudentRepositoryHiber 
	            extends CrudRepository<Student, Long> {
	 
	}

