package com.affiliate.customer.service;

public interface EmailService {
	public boolean sendEmail(String from,String to,String subject,String messageBody) ;
}
