
package com.student.app.xss;

/**
 *
 * @author girish
 */
public class ValidCheck {
	public static String fixSpecialHTMLCharacters(String input) {
		 return fixSpecialHTMLCharacters(input,"");
	}
	
	public static String fixSpecialHTMLCharacters(String input, String URL) {
		
		if (input == null) {
			return null;
		}
			else {
			int inputLength = input.length();
			StringBuilder sb = new StringBuilder(inputLength);
			//System.out.println("The string being cleaned in RequestWrapper is: " + input);
			for (int i = 0; i < inputLength; i++) {
				char c = input.charAt(i);
				if (c == '<')
				{
					sb.append("&lt;");
				}
				else if (c == '>')
				{
					sb.append("&gt;");
				}
				else if (c == '"')
				{
					sb.append("&quot;");
				}
				else{
					sb.append(c);
				}
			}
			//System.out.println("The string after cleaned in RequestWrapper is:  "+sb.toString());
			return sb.toString();
		}
	}
	
}
