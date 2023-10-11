package com.bartosztanski.mailservice.starter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bartosztanski.mailservice.sender.EmailService;

@Component
public class EmailServiceTest implements CommandLineRunner{

	EmailService emailService;
	
	public EmailServiceTest(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@Override
	public void run(String... args) throws Exception {

		emailService.sendSimpleMessage("bartot1999@wp.pl", "Spring boot test", "Message sended successfully");
		
	}

}
