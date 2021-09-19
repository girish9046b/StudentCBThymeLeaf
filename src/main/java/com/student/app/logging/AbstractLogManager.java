package com.student.app.logging;


import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;

/**
 *
 * @author girish
 */
public abstract class AbstractLogManager {
	
	protected ArrayList<SingleLogger> loggers;
	
	public AbstractLogManager() {
		this.loggers = new ArrayList();
	}
	
	protected String getSessionId(HttpSession session) {
		return session.getId();
	}
	protected String getUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}
	protected String getReferrer(HttpServletRequest request) {
		return request.getHeader("Referer");
	}
	protected String getQueryString(HttpServletRequest request) {
		return request.getQueryString();
	}
	protected String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	protected String addDate() {
		java.util.Date date = new java.util.Date();
		String time = new Timestamp(date.getTime()).toString();
		return "time=\"" + time + "\"";
	}
	public void addLogger(SingleLogger singlelogger) {
		this.getLoggers().add(singlelogger);
	}
	public boolean removeLogger(SingleLogger singlelogger) {
		
		SingleLogger loggerToRemove = singlelogger;
		ArrayList<SingleLogger> loggs = this.getLoggers();
		if(loggs.contains(loggerToRemove)) {
			loggs.remove(loggerToRemove);
			return true;
		} else {
			return false;
		}
	}
	public SingleLogger getLogger(String filename) {
		
		try {
			String fileName = filename;
			ArrayList<SingleLogger> loggs = this.getLoggers();
			for (int i = 0; i < loggs.size(); i++) {
				SingleLogger sl = loggs.get(i);
				if(sl.getFileName().equals(fileName)) {
					return sl;
				}
			}
			SingleLogger newLogger = new SingleLogger(fileName);
			loggs.add(newLogger);
			
			return newLogger;
			
		} catch (Exception e) {
			System.out.println("[ SSLogManager ] error : " + e.getMessage());
		}
		return null;
	}
	/**
	 * will log to a filename.log if found, if not found it will be created.
	 * @param filename name of file to write to filename.log
	 * @param level
	 * @param msg entire message to log
	 */
	public void logTo(String filename, Level level, String msg) {
		
		String fileName = filename;
		Level logLevel = level;
		String message = msg;
		try {
			SingleLogger logger = this.getLogger(fileName);
			logger.logSingle(message, logLevel);
		} catch (NullPointerException e) {
			System.out.println("[ SSLogManager ] error : " + e.getMessage());
		}
	}
	/**
	 * logs to file using key value pair <String event, String data> and Level
	 * level - automatically concatenates a timestamp as &time=... ex: event
	 * could be "userClick" and data could be
	 * "fromHomeToSpecials&resortSelected=XXX" use :
	 * SSLogger.getInstance().log("someEvent",
	 * "userClicked&currentPage=somePage", Level.INFO);
	 *
	 * @param event could be "userClick"
	 * @param data could be "fromHomeToSpecials&resortSelected=XXX"
	 * @param level Level.ERROR DEBUG FATAL INFO OFF WARN
	 */
	public ArrayList<SingleLogger> getLoggers() {
		return this.loggers;
	}
	public void setLoggers(ArrayList<SingleLogger> loggers) {
		this.loggers = loggers;
	}
}
