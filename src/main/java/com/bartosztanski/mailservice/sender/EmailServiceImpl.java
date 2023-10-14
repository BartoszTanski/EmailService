package com.bartosztanski.mailservice.sender;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Value("${user.email.from}")
	private String NOREPLY_ADDRESS;
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;
	
//	@Value("classpath:/mail-icon.png")
//	private Resource resourceFile;
	
	Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    public void sendSimpleMessage(String to, String subject, String text)  {
    	
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
			String text, String pathToAttachment) throws MessagingException,
			MailAuthenticationException, MailSendException  {
		
		LOGGER.info("EmailServiceImpl: Sending Simple Message Email With Attachment");
		MimeMessage message = emailSender.createMimeMessage();
	     
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    
	    helper.setFrom(NOREPLY_ADDRESS);
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(text);
	        
	    FileSystemResource file 
	      = new FileSystemResource(new File(pathToAttachment));
	    helper.addAttachment("Invoice", file);
	    
	}
	
	@Override
	public void sendMessageWithTemplate(String to, String subject,
			SimpleMailMessage template, String pathToAttachment) throws MessagingException {
		
		LOGGER.info("EmailServiceImpl: Sending Simple Message(Template) Email With Attachment");
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

	@Override
	public void sendMessageUsingThymeleafTemplate(String to, String subject,
			String htmlTemplatePath, Map<String, Object> templateModel,
			File attachment) throws MessagingException {
		
		MimeMessage message = emailSender.createMimeMessage();
	     
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    
	    Context thymeleafContext = new Context();
	    thymeleafContext.setVariables(templateModel);
	    String htmlBody = thymeleafTemplateEngine.process("template-thymeleaf.html", thymeleafContext);
	    
	    helper.setFrom(NOREPLY_ADDRESS);
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(htmlBody,true);
	    
	       if (attachment!=null) helper.addAttachment("Invoice", attachment);

	    emailSender.send(message);
		
	}

	@Override
	public void sendMessageWithHTML(String to, String subject, String htmlBody,
			File attachment) throws MessagingException {
		
		MimeMessage message = emailSender.createMimeMessage();
	    
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    
	    helper.setFrom(NOREPLY_ADDRESS);
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(htmlBody,true);

	    if (attachment!=null) {
	    	if (attachment.exists())
		    helper.addAttachment(attachment.getName(), attachment);
	    }
	    emailSender.send(message);
		
	}
	
    
    
}