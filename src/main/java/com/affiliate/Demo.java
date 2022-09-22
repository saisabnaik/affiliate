package com.affiliate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Demo {

	public static void main(String[] args) {
		Random rand = new Random();
		System.out.printf("%04d%n", rand.nextInt(10000));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss.SSSS");
		LocalDateTime now = LocalDateTime.now();
		String time = dtf.format(now);
		System.out.println(time);
		String otp = time.split("\\.")[1];

		System.out.println("formatted date: " + otp);

	}

}
