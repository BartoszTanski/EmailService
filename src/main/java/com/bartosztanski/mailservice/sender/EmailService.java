package com.bartosztanski.mailservice.sender;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.bartosztanski.mailservice.model.SimpleMailRequest;
import com.bartosztanski.mailservice.model.TemplateMailRequest;
import com.bartosztanski.mailservice.model.TemplateModel;

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
	
	public void sendMessageUsingThymeleafTemplate(String to, String subject,
			String htmlTemplatePath, Map<String, Object> templateModel,
			File attachment) throws MessagingException;
	
	public void sendMessageWithHTML(String to, String subject, String htmlBody, File attachment)
			throws MessagingException;

	public void sendSimpleMessage(SimpleMailRequest simplemailRequest);

	public List<String> getAllTemplates();

	public void sendMessageWithHTML(SimpleMailRequest simplemailRequest);

	public void sendMessageWithTemplate(TemplateMailRequest templateMailRequest);

	void sendMessageUsingThymeleafTemplate(String to, String subject, String htmlTemplatePath,
			TemplateModel templateModel, File attachment) throws MessagingException;
	
}
