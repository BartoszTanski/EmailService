package com.bartosztanski.mailservice.sender;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.SendFailedException;

@Service
public interface EmailService {

	public void sendSimpleMessage(String to, String subject, String text) throws SendFailedException;
	
	public void sendMessageWithAttachment(String to, String subject,
			String text, String pathToAttachment) throws MessagingException, SendFailedException;

	public void sendMessageWithTemplate(String to, String subject,
			SimpleMailMessage template,
			String pathToAttachment) throws MessagingException, SendFailedException;
}
