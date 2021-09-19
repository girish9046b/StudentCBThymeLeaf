package com.student.app.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.model.Student;

@Repository(value="studentdaomysqlprocedure")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentmysqlProcedureDAO implements StudentDaoIntf{

	 @Autowired
	  @Qualifier("mysqlTemplate4")
	  private NamedParameterJdbcTemplate jdbcTemplate; 
	 
	 @Autowired
	 @Qualifier("mysqlcall4")
	 private SimpleJdbcCall simpleJdbcCall;
	 
	  @Autowired
	  @Qualifier("mysqlDataSource4")
	  private DataSource dataSource;
	
	  public ArrayList<Student> getAllStudents_onlyresultset() throws Exception {
			 String sql = "{call findAllStudents(:NAMEIN)}";
			 MapSqlParameterSource params = new MapSqlParameterSource();
			 params.addValue("NAMEIN", "MARTHAM",Types.VARCHAR);
			 ArrayList<Student> studentslist = (ArrayList<Student>) jdbcTemplate.query(sql,params,new StudentRowMapper());
			 System.out.println(".............studentslist......................"+studentslist);
			 return studentslist;
		    }
	  
	  
	 public ArrayList<Student> getAllStudents() throws Exception {
//		 String sql = "{call findAllStudents(:NAMEIN)}";
		 MapSqlParameterSource params = new MapSqlParameterSource();
		 params.addValue("NAMEIN", "MARTHAMbabu",Types.VARCHAR);
//		 ArrayList<Student> studentslist = (ArrayList<Student>) jdbcTemplate.query(sql,params,new StudentRowMapper());
//		 System.out.println(".............studentslist......................"+studentslist);
		
		 		simpleJdbcCall.withProcedureName("findAllStudents").withCatalogName("student");
		      //SqlParameterSource in = new MapSqlParameterSource().addValue("NAMEIN", "MARTHAM");
		      Map out = simpleJdbcCall.execute(params);
		      System.out.println(".............studentslist......................"+out);
		      System.out.println(".............studentslist......................"+out.get("nameout"));
		      System.out.println(".............studentslist......................"+out.get("#result-set-1"));
		      System.out.println(".............markslist......................"+out.get("#result-set-2"));
		      ArrayList<Student> studentslist=(ArrayList<Student>) out.get("#result-set-1");
	        return studentslist;
	    }
	 
	 
	 
	 public Student getStudent(String id) throws Exception {
		 String sql = "call findStudentByID (:id)";
		 MapSqlParameterSource params = new MapSqlParameterSource();
		 params.addValue("id", id);
		// Student student = (Student) jdbcTemplate.query(sql, new CustomerRowMapper()); 
		 Student student =  (Student)jdbcTemplate.queryForObject(sql, params, new StudentRowMapper());
		 System.out.println(".............studentstudent......................"+student);
		 //Student student = (Student) jdbcTemplate.queryForObject(sql, params, new StudentRowMapper()); 
	        return student;
		}
	 
	 

	  
	  
	 public void addStudent(Student student) throws Exception {
		 String sql = "{call addStudent(:name,:age,:phone,:country,:gender,:standard,:address,:acceptForm)}";
		 MapSqlParameterSource params = new MapSqlParameterSource();
		// params.addValue("id", student.getId());
		 params.addValue("name", student.getName());
		 params.addValue("age", student.getAge());
		 params.addValue("phone", student.getPhone());
		 params.addValue("country", student.getCountry());
		 params.addValue("gender", student.getGender());
		 params.addValue("standard", student.getStandard());
		 params.addValue("address", student.getAddress());
		 params.addValue("acceptForm", student.getAcceptForm());
		 System.out.println(".............addStudentaddStudent......................");
		 jdbcTemplate.update(sql,params);
		}
	 
	 
	 public void UpdateStudent(Student student) throws Exception {
		 String sql = "{call updateStudent(:id,:name,:age,:phone,:country,:gender,:standard,:address,:acceptForm)}";
		 MapSqlParameterSource params = new MapSqlParameterSource();
		 params.addValue("id", student.getId());
		 params.addValue("name", student.getName());
		 params.addValue("age", student.getAge());
		 params.addValue("phone", student.getPhone());
		 params.addValue("country", student.getCountry());
		 params.addValue("gender", student.getGender());
		 params.addValue("standard", student.getStandard());
		 params.addValue("address", student.getAddress());
		 params.addValue("acceptForm", student.getAcceptForm());
		 
		 int i = jdbcTemplate.update(sql,params);
		 System.out.println(".............UpdateStudent......................"+i);
		}
	
	 
	 public void deleteStudent(String id) throws Exception {
		 String sql = "{call deleteStudent(:id)}";
		 MapSqlParameterSource params = new MapSqlParameterSource();
		 params.addValue("id", id);
		 int i = jdbcTemplate.update(sql, params);
		 System.out.println(".............deleteStudent......................"+i);
		}
	
}
