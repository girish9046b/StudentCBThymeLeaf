//package com.student.app.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//
//import com.student.app.couchbase.CBSession;
//import com.student.app.model.Login;
//
//@Configuration
//@Order(1)
//public class DefaultFilter implements Filter {
//	
//	@Autowired
//	CBSession cbSession;
//	
//	
//	@Override
//	 public void init(FilterConfig filterConfig) throws ServletException {
//	  System.out.println("########## Initiating Custom filter ##########");
//	 }
//
//	 @Override
//	 public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//	  HttpServletRequest request = (HttpServletRequest) servletRequest;
//	  HttpServletResponse response = (HttpServletResponse) servletResponse;
//	  HttpSession session = request.getSession();
//	  Login login = (Login) cbSession.getSession(session, "login", Login.class);
//	  cbSession.setSession(session, "loginNN", "GIRISH TEST");
//	  System.out.println("######################################## "+login.getUserName());
//	  String uri = request.getRequestURI()==null?"": request.getRequestURI();
//	  boolean noResource = !uri.contains("/webjars") && !uri.contains("/img") && !uri.contains("/css") && !uri.contains("/js") ;
//	  if(noResource) {
//	  System.out.println("########## Logging Request  {} : {}"+ request.getMethod()+">>>>>>>>>>"+ request.getRequestURI());
//
//	  
//
//	  System.out.println("########## Logging Response :{}"+ response.getContentType());
//	  }
//	//call next filter in the filter chain
//	  filterChain.doFilter(request, response);
//	 }
//
//	 @Override
//	 public void destroy() {
//	  // TODO: 7/4/18
//	 }
//
//	
//}
