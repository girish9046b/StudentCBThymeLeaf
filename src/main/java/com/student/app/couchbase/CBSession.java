package com.student.app.couchbase;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CBSession {
	
	@Autowired
	Gson gson;

	//Code to set in couchbase session
		public void setSession(HttpSession session,String name, Object entityobj) {
			
			ObjectMapper mapper = new ObjectMapper();
	        try {
//	        	session.setAttribute(name,convertToRawJsonDoc(name,entityobj));
				session.setAttribute(name,mapper.writeValueAsString(entityobj));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Code to set in couchbase session
				public void setSession(HttpSession session,String name, String value) {
			        try {
						session.setAttribute(name,value);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
//		//Code to set in couchbase session
//			public <T> T setSession_old(HttpSession session,String name, T entityobj) {
//				T object = null;
//				ObjectMapper mapper = new ObjectMapper();
//		        try {
////		        	session.setAttribute(name,convertToRawJsonDoc(name,entityobj));
//					session.setAttribute(name,gson.toJson(entityobj));
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		        return object;
//			}
		
		//Code to set in couchbase session
			public <T> T  getSession(HttpSession session,String name,Class<T> theClass) {
				 T object = null;
				ObjectMapper mapper = new ObjectMapper();
		        try {
//		        	object=_gson.fromJson(session.getAttribute(name).toString(), Login.class);
		        	System.out.println(theClass.newInstance()+"/////////////////////////////////"+session.getAttribute(name));
		       // Object value= session.getAttribute(name)==null?theClass.newInstance():session.getAttribute(name);
		        	if(session.getAttribute(name)!=null) {
		        	object = (T) mapper.readValue( session.getAttribute(name).toString(), theClass);
		        	}
		        	else {
		        		object = theClass.newInstance();
		        	}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        return  object;
			}
			
			//Code to set in couchbase session
			public String  getSession(HttpSession session,String name) {
				String value="";
		        try {
		        	 value=(String)(session.getAttribute(name)==null?"":session.getAttribute(name));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        return  value;
			}
			
			
}
