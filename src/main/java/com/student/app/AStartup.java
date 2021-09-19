package com.student.app;


import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class AStartup implements ApplicationListener<ContextClosedEvent>  {
	
//check session expiry
//	
//	@EventListener
//    public void onServerStartUp(ContextStartedEvent event) {
//       System.out.println("===================================onServerStartUp=====================================");
//    }
//	@EventListener
//    public void onServerShutDown(ContextClosedEvent event) {
//       System.out.println("===================================onServerShutDown=====================================");
//    }
	@Override
	public void onApplicationEvent(final ContextClosedEvent event) {
		// TODO Auto-generated method stub
//		 System.out.println(hikariDataSource.getPoolName()+"===================================onApplicationEvent====================================="+event);
//		 dataSourceManager.close();
//		
//		 System.out.println(hikariDataSource.getPoolName()+"===================================onApplicationEvent====================================="+event);
		
		// addTimeoutCookies(servletRequest, servletResponse);
	}
	
//	private void addTimeoutCookies(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
//
//	    long currTime = System.currentTimeMillis();
//	    long expiryTime = currTime + servletRequest.getSession().getMaxInactiveInterval() * 1000;
//
//	    Cookie serverTimeCookie = new Cookie("serverTime", "" + currTime);
//	    serverTimeCookie.setPath("/");
//	    servletResponse.addCookie(serverTimeCookie);
//
//	    Cookie expiryCookie = new Cookie("sessionExpiry", "" + expiryTime);
//	    expiryCookie.setPath("/");
//	    servletResponse.addCookie(expiryCookie);
//	}
	
	
	
}
