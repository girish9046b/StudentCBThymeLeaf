package com.student.app.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.student.app.model.Student;


public class StudentRowMapper implements RowMapper {  
 public Student mapRow(ResultSet rs, int rowNum) throws SQLException {  
	 Student student = new Student();  
	 student.setName(rs.getString("name"));
	 student.setId(rs.getLong("id"));
	 student.setAge(rs.getString("age"));
	 student.setGender(rs.getString("gender"));
	 student.setPhone(rs.getString("phone"));
	 student.setCountry(rs.getString("country"));
	 student.setStandard(rs.getString("standard"));
	 student.setAddress(rs.getString("address"));
	 student.setAcceptForm(rs.getString("acceptForm"));
  return student;  
 }  
}  