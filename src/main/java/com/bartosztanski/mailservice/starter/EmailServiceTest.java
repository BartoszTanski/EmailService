/* package com.bartosztanski.mailservice.starter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bartosztanski.mailservice.model.TemplateMailRequest;
import com.bartosztanski.mailservice.service.EmailService;

@Component
public class EmailServiceTest implements CommandLineRunner{

	EmailService emailService;
	
	public EmailServiceTest(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@Value("${spring.mail.templates.path}")
    private String mailTemplatesPath;
	
	@Override
	public void run(String... args) throws Exception {


		Map<String, Object> emailAtributes  = new HashMap<>();
		emailAtributes.put("recipientName", "John Snow");
		emailAtributes.put("link", "https://bartosztanski.azurewebsites.net");
		emailAtributes.put("senderName", "Bartosz T");

		TemplateMailRequest templateMailRequest = new TemplateMailRequest();
		templateMailRequest.setTo("b.t4nsky@gmail.com");
		templateMailRequest.setSubject("Thymeleaf template test");
		templateMailRequest.setTemplateName("template-thymeleaf");
		templateMailRequest.setVariablesMap(emailAtributes);
		templateMailRequest.setAttachment(null);
		
		//emailService.sendTemplateMessage(templateMailRequest);
		
		//emailService.getAllTemplates();
		
	}

}
*/