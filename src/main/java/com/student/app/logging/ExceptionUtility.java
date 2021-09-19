package com.student.app.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 *
 * @author girish
 */
public class ExceptionUtility {

	//Method to get stack trace from the Exception
	public String getStackTrace(Exception e) {
		Writer writer = new StringWriter();
		String s = "";
		try {
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			s = writer.toString();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}
}

