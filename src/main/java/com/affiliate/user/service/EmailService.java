package com.affiliate.user.service;

public interface EmailService {
	public boolean sendEmail(String from,String to,String subject,String messageBody) ;
}
