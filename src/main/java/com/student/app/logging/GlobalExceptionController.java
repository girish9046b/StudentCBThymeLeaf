package com.student.app.logging;
//package com.student.app.logging;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.http.HttpServletRequest;
//
//
//@ControllerAdvice
//public class GlobalExceptionController {
//
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleExcecption(HttpServletRequest request, Exception e) {
//        ModelAndView modelAndView = new ModelAndView();
//        System.err.println("............handleExcecptionhandleExcecptionhandleExcecption....."+e);
//        modelAndView.addObject("exception", e);  
//        modelAndView.setViewName("redirect:/");
//        //mav.addObject("errorcode", "405");
//        return modelAndView;
//    }
//    
//    @RequestMapping("/error")check error handling
//    public String handleError(HttpServletRequest request) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        System.err.println("............handleErrorhandleErrorhandleError....."+status);
//        if (status != null) {
//            Integer statusCode = Integer.valueOf(status.toString());
//         
//            if(statusCode == HttpStatus.NOT_FOUND.value()) {
//                return "error-404";
//            }
//            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                return "error-500";
//            }
//        }
//        return "login";
//    }
//    
//}