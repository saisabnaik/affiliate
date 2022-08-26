package com.affiliate.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender javaMailSender;

	Random random = new Random(1000);

	public void sendEmail(String to) {
		int generatedOtp = random.nextInt(9999);
		String convertedOtp = String.valueOf(generatedOtp);
		String from="mobappssolutions146@gmail.com";
		String subject = "Forgot Password";
		
		String messageBody = "Your One time password is "+convertedOtp+" use this to reset your password this will expire in 5 minutes";
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(messageBody);
		javaMailSender.send(message);

	}


	//@EventListener(ApplicationReadyEvent.class)
	//public void sendMail(String to) {
		//sendEmail(to);

	//}

}
