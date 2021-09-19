//package com.student.aspect;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//
//import com.student.app.logging.AppLogger;
//@Aspect
//@Component
//public class LoggingAspect 
//{
//	@Autowired
//	AppLogger appLogger;
//    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);
//     
//    @Around("execution(* com.student.app..*(..)))" )
//    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable 
//    { Object result =null;
//    	try {
//        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//          
//        //Get intercepted method details
//        String className = methodSignature.getDeclaringType().getSimpleName();
//        String methodName = methodSignature.getName();
//          
//        final StopWatch stopWatch = new StopWatch();
//          
//        //Measure method execution time
//        stopWatch.start();
//         result = proceedingJoinPoint.proceed();
//        stopWatch.stop();
//        //appLogger.aopLog("appLogger................."+appLogger);
//        //Log method execution time
//        appLogger.aopLog("Execution time of " + className + "." + methodName + " "
//                            + ":: " + stopWatch.getTotalTimeMillis() + " ms");
//    	}
//    	catch(Exception e) {
//    		 System.err.println(e.getCause()+"appLogger................."+e.getMessage());
//    		e.printStackTrace();
//    	}
//        return result;
//    }
//}