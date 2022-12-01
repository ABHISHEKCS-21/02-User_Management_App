package com.mypack.Utils;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailsUtils {
	 @Autowired
	    private JavaMailSender mailSender;
	 public boolean sendRegEmail(String subject,String body,String to) {
		boolean isSent= false;
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			
			helper.setSubject("This is an HTML email");
			helper.setTo(to);
			
			boolean html = true;
			helper.setText(body, html);
			mailSender.send(message);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 return isSent ;
	 }
}
