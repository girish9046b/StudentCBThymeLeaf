package com.student.app.rest.restapi;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.student.app.enc.AESEncDec;
import com.student.app.logging.AppLogger;
import com.student.app.model.Login;
import com.student.app.model.Student;
import com.student.app.service.LoginService;
import com.student.app.service.StudentServiceIntf;
import com.student.app.validator.StudentFormValidator;

@CrossOrigin(origins = "http://localhost:4200")
@RefreshScope
@RestController
@RequestMapping("/api/student")
public class RestStudentController {

	@Autowired
	@Qualifier("studentServicemysql") //"studentServiceHiber" //"studentServicemysql" //"studentServicecb"
	StudentServiceIntf studentService; 
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	Student student;
	@Autowired
	StudentFormValidator studentFormValidator;
	@Autowired
	protected AppLogger appLogger;
	@Autowired
	AESEncDec aESEncDec;
	@Autowired
	Gson gson;
	
	@Value("${url.suffex}")
	   String suffix;
	
	@Value("${welcome.message}")
	   String welcomeText;
	@GetMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getMessage() 
	{	
		
		return welcomeText;
		
	}
	
//	document teh :
//		config server - bootstrap.properties
//		config client - application.properties and properties file in local dir
//		postman for acutator "refresh " / post
//		restapi testing with a message
//		
//		Mapped "{[/refresh || /refresh.json],methods=[POST]}"
//		Mapped "{[/dump || /dump.json],methods=[GET]
//		Mapped "{[/heapdump || /heapdump.json],methods=[GET]
//		Mapped "{[/autoconfig || /autoconfig.json],methods=[GET]
//		Mapped "{[/resume || /resume.json],methods=[POST]}"
//		Mapped "{[/configprops || /configprops.json],methods=[GET]
//		Mapped "{[/features || /features.json],methods=[GET]
//		Mapped "{[/loggers/{name:.*}],methods=[GET]
//		Mapped "{[/restart || /restart.json],methods=[POST]}"
//		Mapped "{[/actuator/health],methods=[GET]
//		Mapped "{[/actuator/info],methods=[GET]
//		Mapped "{[/actuator],methods=[GET]
		
		
	
	
	// to load existing student form details
			@GetMapping(value = "/loadStudent/${url.suffex}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
			public  ResponseEntity<String>  loadStudentDetails(@PathVariable("id") String id, HttpSession session) {
				String encString=null;
				try {
				appLogger.appInfoLog("Loading Details to update Student ID : "+id);
				appLogger.appInfoLog("Loading Details to update Student ID : "+aESEncDec.decrypt(id));
				student = studentService.getStudent(aESEncDec.decrypt(id));
				//student = studentService.getStudent(student.getPrefix() + aESEncDec.decrypt(id));
				encString = aESEncDec.encrypt(gson.toJson(student));
				appLogger.appInfoLog("Details Loaded successfully for student : "+student.toString());
				}
				catch (Exception e) {//use the generics for enc and dec
					appLogger.appErrorLog("Exception : ", e);
				}
				return new ResponseEntity<String>(encString, HttpStatus.OK);
			}
		
			
			
			@GetMapping(value = "/allstudentss${url.suffex}", produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<String> getAllStudentss(Model model) 
			{		String encString=null;
					ArrayList<Student> studentslist = null;
				try {
						studentslist = studentService.getAllStudents();
						for(int i=0;i<11;i++) {
						studentslist.addAll(studentslist);
						}
						encString = aESEncDec.encrypt(gson.toJson(studentslist));
				
				}catch (Exception e) {
					appLogger.appErrorLog("Exception : ", e);
				}
				return new ResponseEntity<String>(encString, HttpStatus.OK);
			}
			
			
//	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value = "/allstudents", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Student>> getAllStudents(Model model) 
	{
			ArrayList<Student> studentslist = null;
		try {
				studentslist = studentService.getAllStudents();
		
		}catch (Exception e) {
			appLogger.appErrorLog("Exception : ", e);
		}
		return new ResponseEntity<ArrayList<Student>>(studentslist, HttpStatus.OK);
	}
	
//	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping(value = "/loginCheck", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Login> loginCheck(@RequestBody Login login ) 
	{
		System.out.println(loginService+".......loginloginloginloginlogin............"+login);
		Login loginDoc = null;
		try {
			 loginDoc = loginService.isValidUser(login);
		
		}catch (Exception e) {
			appLogger.appErrorLog("Exception : ", e);
		}
		return new ResponseEntity<Login>(loginDoc, HttpStatus.OK);
	}
	
//	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value = "/getstudent/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Student> getstudent(@PathVariable("id") String id ) 
	{
			Student student = null;
		try {
			student = studentService.getStudent(id);
		
		}catch (Exception e) {
			appLogger.appErrorLog("Exception : ", e);
		}
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	
	// to update existing student form details
			@PostMapping(value = "/addStudentDetails", consumes = "application/json", produces = "application/json")
			public ResponseEntity<Student>  addStudentDetails(@RequestBody Student student) {
				try {
				appLogger.appInfoLog("updating Details for student : "+student.toString());
				studentService.addStudent(student);
				appLogger.appInfoLog("Student details updated successfully: "+student.toString());
				}
				catch (Exception e) {
					appLogger.appErrorLog("Exception : ", e);
				}
				return new ResponseEntity<Student>(student, HttpStatus.CREATED);
			}
			
	// to update existing student form details
//		@PostMapping(value = "/addStudentDetails", produces = "application/json")
//		public ResponseEntity<Student>  addStudentDetails(@RequestBody Student student) {
//			try {
//			appLogger.appInfoLog("updating Details for student : "+student.toString());
//			studentService.addStudent(student);
//			appLogger.appInfoLog("Student details updated successfully: "+student.toString());
//			}
//			catch (Exception e) {
//				appLogger.appErrorLog("Exception : ", e);
//			}
//			return new ResponseEntity<Student>(student, HttpStatus.CREATED);
//
//		}
		
		// to update existing student form details
				@PutMapping(value = "/updateStudentDetails/{id}", consumes = "application/json" , produces = "application/json")
				public ResponseEntity<Student>  updateStudentDetails(@PathVariable("id") String id,@RequestBody Student student) {
					try {
					appLogger.appInfoLog("updating Details for student : "+student.toString());
					student.setId(Long.parseLong(id));
					studentService.UpdateStudent(student);
					appLogger.appInfoLog("Student details updated successfully: "+student.toString());
					}
					catch (Exception e) {
						appLogger.appErrorLog("Exception : ", e);
					}
					return new ResponseEntity<Student>(student, HttpStatus.CREATED);

				}
		
		//to delete existing student details from list
				@DeleteMapping(value="/deleteStudentDetails/{id}" , consumes = "application/json", produces = "application/json")
				public ResponseEntity<String> deleteStudentDetails(@PathVariable("id") String id ) {
					try {
						System.out.println("deleteStudentDetails............."+id);
						studentService.deleteStudent(id);
						//studentService.deleteStudent(student.getPrefix()+id);
						appLogger.appInfoLog("Delete StudentDetails  successfully for ID : "+id);
					}
					catch (Exception e) {
						appLogger.appErrorLog("Exception at loadStudentList ", e);
					}
					return new ResponseEntity<String>(id , HttpStatus.CREATED);
				}
//				
//				
//				@GetMapping(value = "/allstudents2", produces = MediaType.APPLICATION_JSON_VALUE)
//				public ArrayList<Student> getAllEmployeesJSON2(Model model) 
//				{
//						ArrayList<Student> studentslist = null;
//					try {
//							studentslist = studentService.getAllStudents();
//					
//					}catch (Exception e) {
//						appLogger.appErrorLog("Exception : ", e);
//					}
//					return studentslist;
//				}
	
}







// package com.student.app.rest.restapi;
//
//import java.util.ArrayList;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.google.gson.Gson;
//import com.student.app.dao.StudentCBDAO;
//import com.student.app.enc.AESEncDec;
//import com.student.app.logging.AppLogger;
//import com.student.app.model.Student;
//import com.student.app.service.StudentServiceCB;
//import com.student.app.service.StudentServiceIntf;
//import com.student.app.validator.StudentFormValidator;
//
////@CrossOrigin
//@RestController
//@RequestMapping("/api/student")
//public class RestStudentController {
//
//	@Autowired
//	@Qualifier("studentServicemysql") //"studentServiceHiber" //"studentServicemysql" //"studentServicecb"
//	StudentServiceIntf studentService; 
//	@Autowired
//	Student student;
//	@Autowired
//	StudentFormValidator studentFormValidator;
//	@Autowired
//	protected AppLogger appLogger;
//	@Autowired
//	AESEncDec aESEncDec;
//	@Autowired
//	Gson gson;
//	
//	
//	// to load existing student form details
//			@GetMapping(value = "/loadStudent/${url.suffex}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//			public  ResponseEntity<String>  loadStudentDetails(@PathVariable("id") String id, HttpSession session) {
//				String encString=null;
//				try {
//				appLogger.appInfoLog("Loading Details to update Student ID : "+id);
//				appLogger.appInfoLog("Loading Details to update Student ID : "+aESEncDec.decrypt(id));
//				student = studentService.getStudent(aESEncDec.decrypt(id));
//				//student = studentService.getStudent(student.getPrefix() + aESEncDec.decrypt(id));
//				encString = aESEncDec.encrypt(gson.toJson(student));
//				appLogger.appInfoLog("Details Loaded successfully for student : "+student.toString());
//				}
//				catch (Exception e) {//use the generics for enc and dec
//					appLogger.appErrorLog("Exception : ", e);
//				}
//				return new ResponseEntity<String>(encString, HttpStatus.OK);
//			}
//		
//			
//			
//			@GetMapping(value = "/allstudentss${url.suffex}", produces = MediaType.APPLICATION_JSON_VALUE)
//			public ResponseEntity<String> getAllStudentss(Model model) 
//			{		String encString=null;
//					ArrayList<Student> studentslist = null;
//				try {
//						studentslist = studentService.getAllStudents();
//						for(int i=0;i<11;i++) {
//						studentslist.addAll(studentslist);
//						}
//						encString = aESEncDec.encrypt(gson.toJson(studentslist));
//				
//				}catch (Exception e) {
//					appLogger.appErrorLog("Exception : ", e);
//				}
//				return new ResponseEntity<String>(encString, HttpStatus.OK);
//			}
//			
//			
////	@CrossOrigin(origins = "http://localhost:8080")
//	@GetMapping(value = "/allstudents", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<ArrayList<Student>> getAllStudents(Model model) 
//	{
//			ArrayList<Student> studentslist = null;
//		try {
//				studentslist = studentService.getAllStudents();
//		
//		}catch (Exception e) {
//			appLogger.appErrorLog("Exception : ", e);
//		}
//		return new ResponseEntity<ArrayList<Student>>(studentslist, HttpStatus.OK);
//	}
//	
//	
//	
//	// to update existing student form details
//		@PostMapping(value = "/addStudentDetails", produces = "application/json")
//		public ResponseEntity<Student>  addStudentDetails(@RequestBody Student student) {
//			try {
//			appLogger.appInfoLog("updating Details for student : "+student.toString());
//			studentService.addStudent(student);
//			appLogger.appInfoLog("Student details updated successfully: "+student.toString());
//			}
//			catch (Exception e) {
//				appLogger.appErrorLog("Exception : ", e);
//			}
//			return new ResponseEntity<Student>(student, HttpStatus.CREATED);
//
//		}
//		
//		// to update existing student form details
//				@PutMapping(value = "/updateStudentDetails/{id}", produces = "application/json")
//				public ResponseEntity<Student>  updateStudentDetails(@PathVariable("id") String id,@RequestBody Student student) {
//					try {
//					appLogger.appInfoLog("updating Details for student : "+student.toString());
//					student.setId(Long.parseLong(id));
//					studentService.UpdateStudent(student);
//					appLogger.appInfoLog("Student details updated successfully: "+student.toString());
//					}
//					catch (Exception e) {
//						appLogger.appErrorLog("Exception : ", e);
//					}
//					return new ResponseEntity<Student>(student, HttpStatus.CREATED);
//
//				}
//		
//		//to delete existing student details from list
//				@DeleteMapping(value="/deleteStudentDetails/{id}" ,produces = "application/json")
//				public void deleteStudentDetails(@PathVariable("id") String id ) {
//					try {
//						studentService.deleteStudent(id);
//						//studentService.deleteStudent(student.getPrefix()+id);
//						appLogger.appInfoLog("Delete StudentDetails  successfully for ID : "+id);
//					}
//					catch (Exception e) {
//						appLogger.appErrorLog("Exception at loadStudentList ", e);
//					}
//				}
////				
////				
////				@GetMapping(value = "/allstudents2", produces = MediaType.APPLICATION_JSON_VALUE)
////				public ArrayList<Student> getAllEmployeesJSON2(Model model) 
////				{
////						ArrayList<Student> studentslist = null;
////					try {
////							studentslist = studentService.getAllStudents();
////					
////					}catch (Exception e) {
////						appLogger.appErrorLog("Exception : ", e);
////					}
////					return studentslist;
////				}
//	
//}
//
//
