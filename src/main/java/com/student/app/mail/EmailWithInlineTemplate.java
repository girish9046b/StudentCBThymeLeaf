package com.student.app.mail;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
@Service
public class EmailWithInlineTemplate {
	@Autowired
	private JavaMailSender mailSender;

	public void sendInlineMailWithAttachment(String to,String emailFrom, String subject, String body) 
	{
		
	
	    MimeMessagePreparator preparator = new MimeMessagePreparator() 
	    {
	        public void prepare(MimeMessage mimeMessage) throws Exception 
	        {
	            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	            mimeMessage.setFrom(new InternetAddress(emailFrom));
	            Address[] address = new Address[1];
	            address[0]=new InternetAddress(emailFrom);
	            mimeMessage.setFrom(new InternetAddress(emailFrom));
	            mimeMessage.addFrom(address);
	            mimeMessage.setSubject(subject);
	             
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	             
	            helper.setText(body, true);
	             
//	            FileSystemResource res = new FileSystemResource(new File(fileToAttach));
//	            helper.addInline("identifier1234", res);
	        }
	    };
	     
	    try {
	        mailSender.send(preparator);
	    }
	    catch (MailException ex) {
	        // simply log it and go on...
	        System.err.println(ex.getMessage());
	    }
	}
}