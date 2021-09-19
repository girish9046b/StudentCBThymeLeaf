package com.student.app.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.student.app.model.Login;
import com.student.app.rest.resttemplate.RestTemplateStudent;
import com.student.app.service.LoginService;
import com.student.app.service.StudentServiceCB;
import com.student.app.util.Messages;

@Controller
public class LoginController extends BaseController{  //TRY SSL / WILDFLY / CHECK THE EXECUTION PRIORITY OF DIFFERENT SCOPES OF @COMPONENT

	@Autowired
	LoginService loginService;
	@Autowired
	StudentServiceCB studentService;
	@Autowired
	Messages messages;
	@Autowired
	Login login;
	
	@Autowired
	RestTemplateStudent restTemplateStudent;
	
	
	//to load login form and home screen
	@GetMapping("/")
	public String login(Model model,HttpSession session) throws Exception {
		String page="";
//		try {
		login = new Login();
		model.addAttribute("login", login);
		boolean isValidLogin = util.isValidLogin(session);
		if(isValidLogin) {
			page = "redirect:/loadStudentList";
		}
		else {
			page = "login";
			//page = "studentslistJquery";
		}
		//code to access the rest api
//				restTemplateStudent.getAllStudents();
//				restTemplateStudent.addStudent();
//				restTemplateStudent.getAllStudents();
//				restTemplateStudent.updateStudent();
//				restTemplateStudent.getAllStudents();
//				restTemplateStudent.deleteStudent();
//				restTemplateStudent.getAllStudents();
				
		appLogger.appInfoLog("User in startup page : "+page);
//		}
//		catch (Exception e) {
//			appLogger.appErrorLog("Exception : ", e);
//		}
		return page;
	}
	
	@GetMapping("/accessdenied")
	public String serverError(Model model,HttpSession session) throws Exception {
		String page="";
			page = "error/access-denied";
				
		appLogger.appInfoLog("User in access-denied page : "+page);
		return page;
	}
	
//	private int getErrorCode() {
//    	Object c=null;
//		return (Integer)c;
////        return (Integer) httpRequest
////          .getAttribute("javax.servlet.error.status_code");
//    }
	
//to check the user credentials and load the existing students list
	@PostMapping("/LoginCheck")
	public ModelAndView LoginCheck(@Valid Login login, BindingResult bindingResult, HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//try {
//			System.out.println("LoginCheckLoginCheckLoginCheckLoginCheckLoginCheckLoginCheckLoginCheckLoginCheck.................");
//			System.out.println(getErrorCode());
		
			appLogger.appInfoLog("Started logging in for user : "+login.toString());
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("login");
			return modelAndView; 
		}

		Login loginDoc = loginService.isValidUser(login);
		if(loginDoc.getErrmsg().length()<1) {
		cbSession.setSession(session, "login" ,loginDoc);
		cbSession.setSession(session, "loginUserName" ,loginDoc.getUserName());
		}
		
		if(loginDoc.getErrmsg().length()>0) {
			modelAndView.setViewName("login");
			modelAndView.addObject("login", loginDoc);
			return modelAndView;
		
		}
		
//		ArrayList<Student> studentslist = studentService.getAllStudents();
//		modelAndView.addObject("studentslist",studentslist);
//		modelAndView.setViewName("studentslist");
		appLogger.appInfoLog("User loggedIn successfully : "+login.toString());
//		appLogger.appInfoLog("studentslist loaded successfully Size : "+studentslist.size());
		modelAndView.setViewName("redirect:/loadStudentList"); //PRG - POST REDIRECT GET - THIS WILL PREVENT THE LOGIN CODE TO EXECUTE WHEN PAGE IS REFRESHED
//		}
//		catch (Exception e) {
//			appLogger.appErrorLog("Exception : ", e);
//		}
		return modelAndView;
	}

	//to invalidate session and logout
		@GetMapping("/logoutpage")
		public String logout(Model model, HttpSession session, HttpServletRequest  request) throws Exception {
			//try {
			session.invalidate();
			Cookie[] cookies = request.getCookies();
		    if (cookies != null)
		        for (Cookie cookie : cookies) {
		            cookie.setValue("");
		            cookie.setPath("/");
		            cookie.setMaxAge(0);
		        }
			login = new Login();
			model.addAttribute("login", login);
			appLogger.appInfoLog("User Successfully LoggedOut : ");
//			}
//			catch (Exception e) {
//				appLogger.appErrorLog("Exception : ", e);
//			}
			return "login";
		}
}
