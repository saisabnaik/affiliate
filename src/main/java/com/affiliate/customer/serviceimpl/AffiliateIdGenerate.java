package com.affiliate.customer.serviceimpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.affiliate.customer.service.AffiliateIdGenerateService;

@Service
public class AffiliateIdGenerate implements AffiliateIdGenerateService {

	@Override
	public String generateAffiliateId() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss.SSSS");
		LocalDateTime now = LocalDateTime.now();
		String time = dtf.format(now);
		String otp = time.split("\\.")[1];

		Random rand = new Random();
		int num = rand.nextInt(10000);
		String formatted = String.format("%04d", num);

		String random = formatted + otp;

		return random;
	}

}
