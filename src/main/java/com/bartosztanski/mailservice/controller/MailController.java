package com.bartosztanski.mailservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bartosztanski.mailservice.model.SimpleMailRequest;
import com.bartosztanski.mailservice.model.TemplateMailRequest;
import com.bartosztanski.mailservice.sender.EmailService;

import jakarta.mail.SendFailedException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mail/send")
@RequiredArgsConstructor
public class MailController {
	
	private final EmailService emailService;
	
	@PostMapping("/simplemessage")
	public String sendEmail(@RequestBody SimpleMailRequest simplemailRequest) throws SendFailedException {
		emailService.sendSimpleMessage(simplemailRequest);
		return "Simple message sent";
	}
	
	@PostMapping("/templatemessage")
	public String sendEmailWithTemplate(@RequestBody TemplateMailRequest templateMailRequest) {
		emailService.sendMessageWithTemplate(templateMailRequest);
		return "Template message sent";
	}
	
	@PostMapping("/htmlmessage")
	public String sendEmailWithHtml(@RequestBody SimpleMailRequest simplemailRequest) {
		emailService.sendMessageWithHTML(simplemailRequest);
		return "HTML message sent";
	}
	
	@GetMapping("/templates")
	public ResponseEntity<List<String>> getTemplates() {
		List<String> templates = new ArrayList<>();
		templates = emailService.getAllTemplates();
		return ResponseEntity.ok(templates);
	}
}
