package com.student.app.util;

import org.apache.log4j.Level;
import org.springframework.stereotype.Component;

@Component
public class Constants {
	public static final String LOG_FILE_PATH = "D:\\LOGS";
	public static final String UPLOADED_FOLDER = "D:\\LOGS\\files\\";
	public static final String LOG_FILE_PATH_PROD = "../logs/";
	public static final String LOG_FILE_FILE_EXT = ".log";
	public static final String LOG_FILE_MAX_SIZE = "200MB";
	public static final int LOG_FILE_BACKUP_INDEX = 5;
	public static final Level LOG_LEVEL_INFO = Level.INFO;
	public static final Level LOG_LEVEL_WARN = Level.WARN;
	public static final Level LOG_LEVEL_DEBUG = Level.DEBUG;
	public static final Level LOG_LEVEL_ERROR = Level.ERROR;
	public static final String LOG_FILE_NAME_APP_LOG = "app";
	public static final String LOG_FILE_NAME_APP_FLOW_LOG = "app_flow";
	public static final String LOG_FILE_NAME_APP_BOOKING_LOG = "app_booking";
	public static final String LOG_FILE_NAME_APP_POST_CONSTRUCT = "app_post_construct";
	public static final String LOG_FILE_NAME_APP_SESSION_ERROR = "app_session_error";
	public static final String LOG_FILE_NAME_APP_SESSION_DOCS_CLEARED = "app_session_docs_cleared";
	public static final String LOG_FILE_NAME_APP_CLASS_CAST = "app_class_cast";
	public static final String LOG_FILE_NAME_AOP_LOG = "aop";

	
	public static final String ALGORITHM = "AES";
	public static final String ENC_DEC_KEY = "LPeb8I3lYWhieXk9tr+kJw==";
	public static final String UNICODE_FORMAT = "UTF8";
}
