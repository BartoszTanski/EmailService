package com.bartosztanski.mailservice.sender;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.SendFailedException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Value("${user.email.from}")
	private String NOREPLY_ADDRESS;
	
	@Autowired
    private JavaMailSender emailSender;
	
	Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    public void sendSimpleMessage(String to, String subject, String text) throws SendFailedException {
    	
    	LOGGER.info("EmailServiceImpl: Sending Simple Message Email");
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom(NOREPLY_ADDRESS);
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
    }

	@Override
	public void sendMessageWithAttachment(String to, String subject,
			String text, String pathToAttachment) throws MessagingException, SendFailedException {
		
		MimeMessage message = emailSender.createMimeMessage();
	     
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    
	    helper.setFrom(NOREPLY_ADDRESS);
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(text);
	        
	    FileSystemResource file 
	      = new FileSystemResource(new File(pathToAttachment));
	    helper.addAttachment("Invoice", file);
	    
	    emailSender.send(message);
	}
	
	@Override
	public void sendMessageWithTemplate(String to, String subject,
			SimpleMailMessage template, String pathToAttachment) throws MessagingException, SendFailedException {
		
		MimeMessage message = emailSender.createMimeMessage();
	     
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    
	    helper.setFrom(NOREPLY_ADDRESS);
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(template.getText());
	        
	    FileSystemResource file 
	      = new FileSystemResource(new File(pathToAttachment));
	    helper.addAttachment("Invoice", file);

	    emailSender.send(message);
	} 
/*
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("b.t4nsky@gmail.com");
	    mailSender.setPassword("Tajniak32");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
	*/
	
    
    
}