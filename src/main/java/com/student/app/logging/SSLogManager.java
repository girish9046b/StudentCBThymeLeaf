package com.student.app.logging;

import org.apache.log4j.Level;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;


/**
 * non application-specific log manager concrete class
 * @author girish
 */
//@ApplicationScoped
@Component
public class SSLogManager extends AbstractLogManager{
	
	/**
	 * will log to a filename.log if found, if not found it will be created.
	 * @param filename name of file to write to filename.log
	 * @param level
	 * @param msg entire message to log
	 */
	public void logMsgWithSessionID(String filename, Level level, String msg) {
		 String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId()==null?"":RequestContextHolder.currentRequestAttributes().getSessionId();
		String message = msg + " SessionId=\"" + sessionID + "\" ";
		try {
			logTo(filename, level, message);
		} catch (NullPointerException e) {
			System.out.println("[ SSLogManager ] error : " + e.getMessage());
		}
	}
	
}

