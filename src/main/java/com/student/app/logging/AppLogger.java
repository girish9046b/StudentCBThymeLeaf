package com.student.app.logging;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.student.app.util.Constants;
/**
 *
 * @author girish
 */
//@ApplicationScoped
@Component
public class AppLogger {


	 @Autowired
	private HttpServletRequest request;
	 @Autowired
	 private SSLogManager ssLogManager;
	 @Autowired
	 Constants logConstants;


	public void logPostConstruct(String msg) {
		String clientInfo = getClientInfo();
		this.ssLogManager.logTo(logConstants.LOG_FILE_NAME_APP_POST_CONSTRUCT, logConstants.LOG_LEVEL_INFO, clientInfo + msg);
	}
	
	//Flow logs - app_flow
	public void appflowLog(String msg) {
		String clientInfo = getClientInfo();
		this.ssLogManager.logTo(logConstants.LOG_FILE_NAME_APP_FLOW_LOG, logConstants.LOG_LEVEL_INFO, clientInfo + msg);
	}

	public void appflowErrorLog(String msg) {
		String clientInfo = getClientInfo();
		this.ssLogManager.logTo(logConstants.LOG_FILE_NAME_APP_FLOW_LOG, logConstants.LOG_LEVEL_ERROR, clientInfo + msg);
	}

	public void appBookingLog(String msg) {
		String clientInfo = getClientInfo();
		this.ssLogManager.logTo(logConstants.LOG_FILE_NAME_APP_BOOKING_LOG, logConstants.LOG_LEVEL_INFO, clientInfo + msg);
	}

	
	//Info logs - app
//	public void appLog(Level level, String msg) {
//		String clientInfo = getClientInfo();
//		this.ssLogManager.logTo(APP_LOG, level, clientInfo + msg);
//	}

	public void appInfoLog(String msg) {
		String clientInfo = getClientInfo();
		this.ssLogManager.logTo(logConstants.LOG_FILE_NAME_APP_LOG, logConstants.LOG_LEVEL_INFO, clientInfo + msg);
	}

	public void appDebugLog(String msg) {
		String clientInfo = getClientInfo();
		this.ssLogManager.logTo(logConstants.LOG_FILE_NAME_APP_LOG, logConstants.LOG_LEVEL_DEBUG, clientInfo + msg);
	}

	public void appErrorLog(String msg) {
		appErrorLog(msg, null);
	}

	public void appErrorLog(String msg, Exception ex) {
		String clientInfo = getClientInfo();
		String error = getError(ex);
		this.ssLogManager.logTo(logConstants.LOG_FILE_NAME_APP_LOG, logConstants.LOG_LEVEL_ERROR, clientInfo + msg + error);
		//this.appflowErrorLog(msg + error);
	}

	
	
	//session logs - app_session_error
	public void logSessionError(String msg, Exception ex) {
		String clientInfo = getClientInfo();
		String error = getError(ex);
		//this.ssLogManager.logTo(APP_SESSION_ERROR, logConstants.LOG_LEVEL_ERROR, clientInfo + msg + error);
		this.appflowErrorLog(msg + error);
	}

	//session logs - app_session_docs_cleared
	public void logSessionDocsCleared(String msg) {
		String clientInfo = getClientInfo();
		this.ssLogManager.logTo(logConstants.LOG_FILE_NAME_APP_SESSION_DOCS_CLEARED, logConstants.LOG_LEVEL_INFO, clientInfo + msg); 
	}

	//session logs - app_class_cast
	public void logClassCast(String msg) {
		String clientInfo = getClientInfo();
		this.ssLogManager.logTo(logConstants.LOG_FILE_NAME_APP_CLASS_CAST, logConstants.LOG_LEVEL_INFO, clientInfo + msg);
	}
	
	
	public void aopLog(String msg) {
		String clientInfo = getClientInfo();
		this.ssLogManager.logTo(logConstants.LOG_FILE_NAME_AOP_LOG, logConstants.LOG_LEVEL_INFO, clientInfo + msg);
	}
	public String getClientInfo() {
		String clientInfo = "";
		String IP;
		String sessionID;
		String requestedResource;
		try {
			 sessionID = RequestContextHolder.currentRequestAttributes().getSessionId()==null?"":RequestContextHolder.currentRequestAttributes().getSessionId();
			 String ipAddress = getClientIp();
				IP = ", IP=\"" + ipAddress + "\"";
				requestedResource = ", RequestedResource=\"" + request.getRequestURI() + "\" ";
				sessionID = ", SessionId=\"" +sessionID + "\" , ";
				clientInfo = requestedResource +  IP + sessionID;
			

		} catch (Exception e) {
			clientInfo ="";
		}
		return clientInfo;
	}

	private String getClientIp() {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
	
	public String getError(Exception ex) {
		String error = "";
		if (ex != null) {
			error = new ExceptionUtility().getStackTrace(ex);
		}
		return error;
	}


}
