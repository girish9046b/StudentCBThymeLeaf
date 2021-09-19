package com.student.app.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.student.app.couchbase.CBSession;
import com.student.app.logging.AppLogger;
import com.student.app.mail.EmailService;
import com.student.app.mail.EmailWithAttachment;
import com.student.app.mail.EmailWithInlineTemplate;
import com.student.app.model.Login;
import com.student.app.model.Student;
import com.student.app.service.StudentServiceIntf;

@Controller
public class EmailController {

	@Autowired
	EmailService emailService;
	@Autowired
	EmailWithAttachment emailWithAttachment;
	@Autowired
	EmailWithInlineTemplate emailWithInlineTemplate;
	@Autowired
	AppLogger appLogger;
	@Autowired
	@Qualifier("studentServiceHiber") //"studentServiceHiber" //"studentServicemysql" //"studentServicecb"
	StudentServiceIntf studentService; 
	@Autowired
	CBSession cbSession;
	@Autowired
	Gson gson;
	
	
	
	 @Value("${spring.mail.from}") //Defined in application.properties file
	  String emailFrom;
	
	//to send emails
	@GetMapping("/email")
	public String email(HttpSession session) throws Exception {
		String page = "redirect:/loadStudentList";
		//try {
			appLogger.appInfoLog("User in start email page : "+page);
			Login login = (Login) cbSession.getSession(session, "login", Login.class);
			String mailBody = emailService.getMailContent(login);
			emailWithInlineTemplate.sendInlineMailWithAttachment("girish9046@gmail.com",emailFrom,"test", mailBody);
//			emailService.sendMail("girish9046@gmail.com","test","hi girish");
//			emailWithAttachment.sendMailWithAttachment("girish9046@gmail.com",emailFrom,"test","hi girish","D:\\logs\\delete.png");
			appLogger.appInfoLog(emailFrom+"User in finish email page : "+page);
//		}
//		catch (Exception e) {
//			appLogger.appErrorLog("Exception : ", e);
//		}
		return page;
	}
	
	// to load the existing students list
		@PostMapping(value = "/emailTemplate")
		public ModelAndView loadStudentList(HttpSession session,HttpServletRequest request) throws Exception {
			ModelAndView modelAndView = new ModelAndView();
			Login login = null;
			//try {
				//code to read the post parameters form urlconnection
					InputStreamReader inputStreamReader =new InputStreamReader(request.getInputStream());
					String data;
					BufferedReader br = new BufferedReader(inputStreamReader);
					while ((data = br.readLine()) != null) {
					   login = gson.fromJson(data,Login.class);
					}
				  
			ArrayList<Student> studentslist = studentService.getAllStudents();
			modelAndView.addObject("studentslist", studentslist);
			modelAndView.addObject("login", login);
			modelAndView.setViewName("emailTemplate");
//			}
//		catch (Exception e) {
//			appLogger.appErrorLog("Exception : ", e);
//		}
			return modelAndView;
		}
		
		
		
		
}
