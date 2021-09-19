//package com.student.app;
//
////import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertThat;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.CoreMatchers.*;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.student.app.controllers.StudentController;
//import com.student.app.logging.AppLogger;
//import com.student.app.model.Student;
//import com.student.app.service.StudentServiceIntf;
//
//
////................. @Mock annotation to created mock object for EmployeeDAO dependency.
////................. @InjectMocks to create EmployeeController class and also inject the mocked employeeDAO instance.
//
//@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
//public class StudentControllerTest {
//
//	@InjectMocks
//	StudentController studentController;
//	
//	@Mock
//	@Qualifier("studentServicemysql")
//	StudentServiceIntf studentService; 
//	
//	@Mock
//	AppLogger appLogger;
//	
//	@Test
//	public void loadStudentDetailsTest1() throws Exception {
//		MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//		HttpSession session = request.getSession();
//		//MockHttpSession session = new MockHttpSession();
//		List<Student> list = new ArrayList<Student>();
//		Student studentMock0 = new Student(1,"girish","33","988888888","india","M","3","asasasa","Y");
//		Student studentMock1 = new Student(1,"girish","33","988888888","india","M","3","asasasa","Y");
//		Student studentMock2 = new Student(1,"girish","33","988888888","india","M","3","asasasa","Y");
//		Student studentMock3 = new Student(1,"girish","33","988888888","india","M","3","asasasa","Y");
//		list.add(studentMock0);
//        list.add(studentMock1);
//        list.add(studentMock2);
//        list.add(studentMock3);
//        
//       for(Student studentMock:list) {
//        
//		when(studentService.getStudent("2")).thenReturn(studentMock);
//		
//		ModelAndView mv = studentController.loadStudentDetails("2",session);
//		Student Student=(Student) mv.getModel().get("student");
//		System.out.println(".....Student......."+Student);
//		assertTrue(Student.getName().equalsIgnoreCase(studentMock.getName()));//
//		assertThat(Student.getName().equalsIgnoreCase(studentMock.getName()));
//		
//		assertThat(Student.getCountry(), is(equalTo(studentMock.getCountry())));//actual is passed in first and expected second,
//		assertThat(Student.getCountry(), is((studentMock.getCountry())));//actual is passed in first and expected second,
//		assertEquals(studentMock.getCountry() ,Student.getCountry()); //expected value is passed in first and actual second,
//		//assertFalse(studentMock.getCountry().equals(Student.getCountry()));
//		assertThat(1<3);
//		assertThat("adc", containsString("d"));
//		assertThat(Student.getName().equalsIgnoreCase(studentMock.getName()), is(true));//like assertTrue
//		assertThat(!Student.getName().equalsIgnoreCase(studentMock.getName()), is(false));//like assertFalse
//		assertThat(Student.getCountry().equalsIgnoreCase("UK"), is(false));
//       }
//       //verify(studentService, times(1)).getStudent("1");
//	}
//	
//	@Test
//	public void loadStudentDetailsTest2() throws Exception {
//		MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//		HttpSession session = request.getSession();
//		//MockHttpSession session = new MockHttpSession();
//        
//		Student studentMock = new Student(1,"girish11","33","988888888","USA","M","3","asasasa","Y");
//		when(studentService.getStudent("1")).thenReturn(studentMock);
//		
//		ModelAndView mv = studentController.loadStudentDetails("1",session);
//		Student Student=(Student) mv.getModel().get("student");
//		assertFalse(Student.getCountry().equals("UK"));
//		assertThat(Student.getName().equalsIgnoreCase(studentMock.getName()));
//		//assertEquals(Student.getCountry(), "UK");
//        verify(studentService, times(1)).getStudent("1");
//	}
//}
