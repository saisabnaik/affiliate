package com.affiliate.user.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.affiliate.user.service.EmailService;

@Service
public class EmailSenderServiceimpl implements EmailService{

	 @Autowired
	 private JavaMailSender javaMailSender;

	 @Override
	public boolean sendEmail(String from,String to,String subject,String messageBody) {
		boolean f = false;
	
		try {
		
		 SimpleMailMessage message = new SimpleMailMessage();
		 message.setFrom(from);
		 message.setTo(to);
		 message.setSubject(subject);
		 message.setText(messageBody);
		 
		 javaMailSender.send(message);
		 f=true;
		 return f;
		}catch(Exception e) {
			e.printStackTrace();
		}
		 
		 
		return f;

	}

}
