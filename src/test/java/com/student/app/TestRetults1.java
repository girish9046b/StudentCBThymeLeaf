//package com.student.app;
//
//import org.junit.runner.Result;
//
//import org.junit.runner.JUnitCore;
//import org.junit.runner.notification.Failure;
//
//import junit.framework.TestSuite;
//
//public class TestRetults1 {
//
//	public static void main(String args[]) {
//		
//		wirteTestRestults();
//	}
//	
//	
//	public static void wirteTestRestults() { 
//		    System.out.println("Running Junit Test Suite.");
//		     Result result = JUnitCore.runClasses(StudentControllerTest.class);
//		     if(result.getFailureCount()>0) {
//		    	 for (Failure failure : result.getFailures()) {
//			         System.out.println("Failed Test : "+failure.toString());
//			      }
//		     }
//		     
//		      System.out.println("Successful: " + result.wasSuccessful() + 
//		        " ran " + result.getRunCount() +" tests , with failures count : "+result.getFailureCount());
//		   }
//
//		
//	
//}
