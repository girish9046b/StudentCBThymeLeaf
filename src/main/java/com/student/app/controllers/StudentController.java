package com.student.app.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.student.app.couchbase.Counter;
import com.student.app.couchbase.CounterManager;
import com.student.app.model.Student;
import com.student.app.rest.restapi.Response;
import com.student.app.service.StudentServiceIntf;
import com.student.app.validator.StudentFormValidator;

@Controller
public class StudentController extends BaseController{

	@Autowired
	StudentFormValidator studentFormValidator;
	@Autowired
	@Qualifier("studentServiceHiber") //"studentServiceHiber" //"studentServicemysql" //"studentServicecb"
	StudentServiceIntf studentService; 
	@Autowired
	Student student;
	@Autowired
	CounterManager counterManager;
	



	// to load the existing students list
	@GetMapping(value = "/loadStudentList", produces = "application/json")
	public ModelAndView loadStudentList(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//try {
		//response.sendError(500);
		//int k = 9/0;
		ArrayList<Student> studentslist = studentService.getAllStudents();
		modelAndView.addObject("studentslist", studentslist);
		modelAndView.setViewName("studentslist");
		appLogger.appInfoLog("Studentslist loaded successfully Size : "+studentslist.size());
//	}
//	catch (Exception e) {
//		appLogger.appErrorLog("Exception : ", e);
//	}
		return modelAndView;
	}

	// to load existing student form details
	@GetMapping(value = "/loadStudentDetails", produces = "application/json")
	public ModelAndView loadStudentDetails(@RequestParam String id, HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
//		try {
		appLogger.appInfoLog("Loading Details to update Student ID : "+id);
		//student = studentService.getStudent(student.getPrefix() + id);
		student = studentService.getStudent(id);
		System.out.println("objectEncryptionDecryption......................"+student.toString());
		modelAndView.addObject("student", student);
		modelAndView.setViewName("editStudent");
		appLogger.appInfoLog("Details Loaded successfully for student : "+student.toString());
//		}
//		catch (Exception e) {
//			appLogger.appErrorLog("Exception : ", e);
//		}
		return modelAndView;
	}

	// to update existing student form details
	@PostMapping(value = "/updateStudentDetails", produces = "application/json")
	public ModelAndView updateStudentDetails(@Valid @RequestBody Student student, BindingResult bindingResult,HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
//		try {
		appLogger.appInfoLog("updating Details for student : "+student.toString());
		studentFormValidator.validate(student, bindingResult);
		if (bindingResult.hasErrors()) {
			appLogger.appInfoLog("update StudentDetails validation failed  : "+ bindingResult.getAllErrors().toString());
			modelAndView.setViewName("editStudent");
			modelAndView.addObject("student", student);
			return modelAndView;
		}
		studentService.UpdateStudent(student);
		appLogger.appInfoLog("Student details updated successfully: "+student.toString());
//		ArrayList<Student> studentslist = studentService.getAllStudents();
//		modelAndView.addObject("studentslist", studentslist);
//		modelAndView.setViewName("studentslist");
//		appLogger.appInfoLog("Studentslist loaded successfully Size : "+studentslist.size());
		modelAndView.setViewName("studentslist");
//		}
//		catch (Exception e) {
//			appLogger.appErrorLog("Exception : ", e);
//		}
		return modelAndView;

	}

	// to load the add student form
	@GetMapping("addStudentDetails")
	public ModelAndView addStudentDetails(HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
//		try {
		String docid = "" + counterManager.getCounter(Counter.STUDENT);
		Student student = new Student();
		student.setId(Long.parseLong(docid));
		appLogger.appInfoLog("Add StudentDetails form loaded with ID : "+docid);
		modelAndView.addObject("student", student);
		modelAndView.setViewName("addStudent");
//	}
//	catch (Exception e) {
//		appLogger.appErrorLog("Exception : ", e);
//	}
		return modelAndView;

	}

	// to save the new student form details
	@PostMapping("/saveStudentDetails")
	public ModelAndView saveStudentDetails(@Valid Student student, BindingResult bindingResult, HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
//try {
		studentFormValidator.validate(student, bindingResult);
		if (bindingResult.hasErrors()) {
			appLogger.appInfoLog("Save StudentDetails validation failed page : "+ bindingResult.getAllErrors().toString());
			modelAndView.setViewName("addStudent");
			modelAndView.addObject("student", student);
			return modelAndView;
		}
		studentService.addStudent(student);
			appLogger.appInfoLog("Saved StudentDetails successfully: "+student.toString());
//		ArrayList<Student> studentslist = studentService.getAllStudents();
//		modelAndView.addObject("studentslist", studentslist);
//		modelAndView.setViewName("studentslist");
//		appLogger.appInfoLog("Studentslist loaded successfully Size : "+studentslist.size());
			modelAndView.setViewName("redirect:/loadStudentList");
//	}
//	catch (Exception e) {
//		appLogger.appErrorLog("Exception : ", e);
//	}
		return modelAndView;
	}
	
	//to delete existing student details from list
		@PostMapping(value="/deleteStudentDetails" ,produces = "application/json")
		@ResponseBody
		public Response deleteStudentDetails(@RequestBody String id ) throws Exception {
			Response response = new Response("Y", id);
			//try {
			//studentService.deleteStudent(student.getPrefix()+id);
			studentService.deleteStudent(id);
			appLogger.appInfoLog("Delete StudentDetails  successfully for ID : "+id);
//			}
//			catch (Exception e) {
//				appLogger.appErrorLog("Exception at loadStudentList ", e);
//			}
		    return response;
		}

}
