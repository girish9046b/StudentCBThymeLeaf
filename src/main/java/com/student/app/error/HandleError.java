package com.student.app.error;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.student.app.logging.AppLogger;
import com.student.app.logging.ExceptionUtility;


@ControllerAdvice
public class HandleError {
	
	@Autowired
	AppLogger applogger;

	
//	 @ExceptionHandler(Error.class)
//     public String handleError(HttpServletRequest request, Error e)   {
//    	 System.out.println(".......111111..HandleError..............."+e);
//    	 //applogger.appErrorLog("Exception : ", e);
//         return   "redirect:/logoutpage";
//     }
	 
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  @ExceptionHandler(NoHandlerFoundException.class)
	  public String notFoundHandler(Exception e) {
		  System.out.println(".......Item not found. HTTP 500 returneddd................"+e);
	    	 applogger.appErrorLog("Exception : ", e);
	         return   "forward:/accessdenied";
	  }

	 
	 
	 @ExceptionHandler(Exception.class)
     public String handleException(HttpServletRequest request, Exception e)   {
    	 System.out.println(".......222222..HandleError..............."+e);
    	 applogger.appErrorLog("Exception : ", e);
         return   "redirect:/logoutpage";
     }
	  
	  @ExceptionHandler({NullPointerException.class, ArrayIndexOutOfBoundsException.class, IOException.class })//, NoHandlerFoundException.class})
	    public String handleError404(HttpServletRequest request, Exception e)   {
		  System.out.println(".......333333..HandleError..............."+e);
		  applogger.appErrorLog("Exception : ", e);
		  return   "redirect:/logoutpage";
	    }
	  
	  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	    public String handleMethodNotSupportedException(HttpServletRequest request, Exception e)   {
		  System.out.println(".......44444..HandleError..............."+e);
		  applogger.appErrorLog("Exception : ", e);
		  return   "redirect:/logoutpage";
	    }
	
	  @ExceptionHandler(MultipartException.class)
	    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {

	        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
	        return "redirect:/uploadStatus";

	    }
	 
	//for RestController called from jquery , the exception details returned as json format
//	  @ExceptionHandler(value = { Exception.class })
//	  @ResponseBody
//	  public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request) {
//		  applogger.appErrorLog("Exception : ", e);
//		  ErrorMessage errorMessage = new ErrorMessage(new Date(), e.getMessage(),
//			        request.getDescription(true));
//	  
//	          return new ResponseEntity<>(
//	        		  errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//}
	  
	  public String getError(Exception ex) {
			String error = "";
			if (ex != null) {
				error = new ExceptionUtility().getStackTrace(ex);
			}
			return error;
		}
	  
}
