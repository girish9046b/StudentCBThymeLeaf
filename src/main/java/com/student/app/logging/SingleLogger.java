package com.student.app.logging;

import java.sql.Timestamp;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.SimpleLayout;

import com.student.app.util.Constants;
/**
 *
 * @author girish
 */
public class SingleLogger {
	
	private Logger logger;
	private String fileName;
	
//	@Autowired
//	LogConstants logConstants;
//	
	/**
	 * logs to independent file
	 * @param filename filename this logger will log to
	 */
	public SingleLogger(String filename) {
		try {
			if (filename != null && !filename.isEmpty()) {
				this.fileName = filename;
				init();
			} else {
				throw new Exception("filename cannot be null or empty");
			}
		} catch (Exception e) {
			System.out.println("[ SingleLogger ] error : " + e.getMessage());
		}
	}
	
	/**
	 * logs single message to this SingleLogger's filename
	 * @param msg 
	 */
	public void logSingle(String msg, Level level) {
		this.logger.log(level, addDate() + addLogFileName() + msg);
	}
	
	/**
	 * gets the time stamp for log messages
	 */
	 private String addDate() {
	        java.util.Date date = new java.util.Date();
	        String time = new Timestamp(date.getTime()).toString();
	        return "time=\"" + time + "\" ";
	    }
	 
	 /**
		 * gets the Log File Name for log messages
		 */
		 private String addLogFileName() {
			 String filename = this.getFileName();
		        return " LogFileName=\"" + filename + "\" ";
		    }
	 
	private void init() {
		try {
			String filename = this.getFileName();
//			this.logger = Logger.getLogger(filename);
			//fa = new FileAppender(new SimpleLayout(), "d:/logs/" + filename + ".log");
			
			this.logger = Logger.getLogger(filename);
			RollingFileAppender fileAppender = new RollingFileAppender(new SimpleLayout(), Constants.LOG_FILE_PATH + filename + Constants.LOG_FILE_FILE_EXT);
			fileAppender.setMaxFileSize(Constants.LOG_FILE_MAX_SIZE);
			fileAppender.setMaxBackupIndex(Constants.LOG_FILE_BACKUP_INDEX);
//             fa = new FileAppender(new SimpleLayout(), "../logs/" + appName + ".log");
			logger.addAppender(fileAppender);
			
//			logger.addAppender(fa);
//			fa.setLayout(new SimpleLayout());
		} catch (Exception e) {
			System.err.println("[ SingleLogger ] error : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}

