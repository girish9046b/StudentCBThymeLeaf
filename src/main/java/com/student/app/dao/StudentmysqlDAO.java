package com.student.app.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.model.Student;

@Repository(value="studentdaomysql")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentmysqlDAO implements StudentDaoIntf{

	 @Autowired
	  @Qualifier("mysqlTemplate4")
	  private NamedParameterJdbcTemplate jdbcTemplate; 
	 
	 //@Autowired
	 //@Qualifier("mysqlcall4")
	 //private SimpleJdbcCall simpleJdbcCall;
	 
	  @Autowired
	  @Qualifier("mysqlDataSource4")
	  private DataSource dataSource;
	
//	  @Autowired
//	  @Qualifier("mysqlTemplate3")
//	  private NamedParameterJdbcTemplate jdbcTemplate;
//	 
//	  @Autowired
//	  @Qualifier("mysqlDataSource3")
//	  private DataSource dataSource;
	 
	 public ArrayList<Student> getAllStudents() throws Exception {
	        String sql = "SELECT * FROM student";
	        ArrayList<Student> studentslist = (ArrayList<Student>) jdbcTemplate.query(sql,new StudentRowMapper());
	        return studentslist;
//	        try {
//	    		Connection con = dataSource.getConnection();
//	    		System.out.println(".............1111...conconconconconconconcon........"+con);
//	    		con.close();
//	    		} catch (Exception e) {
//	    			// TODO Auto-generated catch block
//	    			e.printStackTrace();
//	    		}
	        
	    }
	 
	 
	 
	 public Student getStudent(String id) throws Exception {
		 String sql = "SELECT * FROM student where id=:id";
		 MapSqlParameterSource params = new MapSqlParameterSource();
		 params.addValue("id", id);
		// Student student = (Student) jdbcTemplate.query(sql, new CustomerRowMapper()); 
		
		 Student student = (Student) jdbcTemplate.queryForObject(sql, params, new StudentRowMapper()); 
		 System.out.println(jdbcTemplate+"///////////222////////"+student);
	        return student;
		}
	 
	 
	 public void addStudent(Student student) throws Exception {
		
		 String sql = "INSERT INTO student (name, phone, age,gender, country,standard,address,acceptForm)"
                 + " VALUES (:name, :phone, :age , :gender, :country , :standard , :address , :acceptForm)";
		 MapSqlParameterSource params = new MapSqlParameterSource();
		 params.addValue("name", student.getName());
		 params.addValue("phone", student.getPhone());
		 params.addValue("country", student.getCountry());
		 params.addValue("standard", student.getStandard());
		 params.addValue("age", student.getAge());
		 params.addValue("gender", student.getGender());
		 params.addValue("address", student.getAddress());
		 params.addValue("acceptForm", student.getAcceptForm());
		 
		 jdbcTemplate.update(sql,params);
		}
	 
	 
	 public void UpdateStudent(Student student) throws Exception {
		 String sql = "UPDATE student SET name=:name , phone=:phone , age=:age , gender=:gender , country=:country , standard=:standard , address=:address ,  acceptForm=:acceptForm WHERE id=:id";
		 MapSqlParameterSource params = new MapSqlParameterSource();
		 params.addValue("id", student.getId());
		 params.addValue("name", student.getName());
		 params.addValue("phone", student.getPhone());
		 params.addValue("country", student.getCountry());
		 params.addValue("standard", student.getStandard());
		 params.addValue("age", student.getAge());
		 params.addValue("gender", student.getGender());
		 params.addValue("address", student.getAddress());
		 params.addValue("acceptForm", student.getAcceptForm());
		 
		 jdbcTemplate.update(sql,params);
		}
	 
	 
	 public void deleteStudent(String id) throws Exception {
		 String sql = "DELETE FROM student WHERE id=:id";
		 MapSqlParameterSource params = new MapSqlParameterSource();
		 params.addValue("id", id);
		    jdbcTemplate.update(sql, params);
		}
	
	 
	
}
