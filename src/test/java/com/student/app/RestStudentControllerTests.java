//package com.student.app;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.student.app.model.Student;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
//public class RestStudentControllerTests {
//
//
//	    @Autowired
//	    private TestRestTemplate restTemplate;
//	     
//	    @LocalServerPort
//	    int randomServerPort;
//	 
//	    @Test
//	    public void testAddEmployeeSuccess() throws URISyntaxException 
//	    {
//	        final String baseUrl = "https://localhost:8443/api/student/loadStudentDetails/7db3b9cf4efbf0ad40a3b0732dec3822";
//	        URI uri = new URI(baseUrl);
//	        Student studentMock0 = new Student(1,"girish","33","988888888","india","M","3","asasasa","Y");
//	         
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.set("X-COM-PERSIST", "true");      
//	 
//	        HttpEntity<Student> request = new HttpEntity<>(studentMock0, headers);
//	         
//	        ResponseEntity<Student> result = this.restTemplate.getForEntity(uri, Student.class);
//	         
//	        //Verify request succeed
//	        Assert.assertEquals(201, result.getStatusCodeValue());
//	    }
//	     
////	    @Test
////	    public void testAddEmployeeMissingHeader() throws URISyntaxException 
////	    {
////	        final String baseUrl = "http://localhost:"+randomServerPort+"/employees/";
////	        URI uri = new URI(baseUrl);
////	        Employee employee = new Employee(null, "Adam", "Gilly", "test@email.com");
////	         
////	        HttpHeaders headers = new HttpHeaders();
////	 
////	        HttpEntity<Employee> request = new HttpEntity<>(employee, headers);
////	         
////	        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
////	         
////	        //Verify bad request and missing header
////	        Assert.assertEquals(400, result.getStatusCodeValue());
////	        Assert.assertEquals(true, result.getBody().contains("Missing request header"));
////	    }
//	 
//	
//
//}
