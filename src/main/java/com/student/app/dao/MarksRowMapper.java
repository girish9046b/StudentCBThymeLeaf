package com.student.app.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.student.app.model.Marks;
import com.student.app.model.Student;


public class MarksRowMapper implements RowMapper {  
 public Marks mapRow(ResultSet rs, int rowNum) throws SQLException {  
	 Marks marks = new Marks();  
	 marks.setStudentid(rs.getInt("studentid"));
	 marks.setSemister(rs.getInt("semister"));
	 marks.setEnglish(rs.getDouble("english"));
	 marks.setMaths(rs.getDouble("maths"));
	 marks.setScience(rs.getDouble("science"));
	 marks.setSocial(rs.getDouble("social"));
  return marks;  
 }  
}  