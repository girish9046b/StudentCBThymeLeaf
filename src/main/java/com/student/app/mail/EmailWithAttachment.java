package com.student.app.mail;

import java.io.File;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
@Service
public class EmailWithAttachment {

	@Autowired
	private JavaMailSender mailSender;
	 
	public void sendMailWithAttachment(String to,String emailFrom, String subject, String body, String fileToAttach) 
	{
	    MimeMessagePreparator preparator = new MimeMessagePreparator() 
	    {
	        public void prepare(MimeMessage mimeMessage) throws Exception 
	        {
	            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	            mimeMessage.setFrom(new InternetAddress(emailFrom));
	            mimeMessage.setSubject(subject);
	            mimeMessage.setText(body);
	             
	            FileSystemResource file = new FileSystemResource(new File(fileToAttach));
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	            helper.addAttachment(fileToAttach, file);
	            helper.setText(body, true);
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