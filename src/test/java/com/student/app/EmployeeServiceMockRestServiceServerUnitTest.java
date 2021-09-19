//package com.student.app;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.client.MockRestServiceServer;
//import org.springframework.web.client.RestTemplate;
//
//import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
//import com.student.app.service.StudentServiceIntf;
//
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = SpringTestConfig.class)
//public class EmployeeServiceMockRestServiceServerUnitTest {
// 
//    @Autowired
//    @Qualifier("studentServicemysql")
//    private StudentServiceIntf studentService ;
//    
//    @Autowired
//    @Qualifier("mysqlDataSource4")
//    private RestTemplate restTemplate;
// 
//    private MockRestServiceServer mockServer;
//    private ObjectMapper mapper = new ObjectMapper();
// 
//    @Before
//    public void init() {
//        mockServer = MockRestServiceServer.createServer(restTemplate);
//    }
//     
//    @Test                                                                                         
//    public void givenMockingIsDoneByMockRestServiceServer_whenGetIsCalled_thenReturnsMockedObject()() {   
//        Employee emp = new Employee("E001", "Eric Simmons");
//        mockServer.expect(ExpectedCount.once(), 
//          requestTo(new URI("http://localhost:8080/employee/E001")))
//          .andExpect(method(HttpMethod.GET))
//          .andRespond(withStatus(HttpStatus.OK)
//          .contentType(MediaType.APPLICATION_JSON)
//          .body(mapper.writeValueAsString(emp))
//        );                                   
//                        
//        Employee employee = empService.getEmployee(id);
//        mockServer.verify();
//        Assert.assertEquals(emp, employee);                                                        
//    }
//}