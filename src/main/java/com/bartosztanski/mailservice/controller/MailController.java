package com.bartosztanski.mailservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bartosztanski.mailservice.model.SimpleMailRequest;

@RestController
@RequestMapping("/mail/send")
public class MailController {
	
	@PostMapping("/simplemessage")
	public String sendEmail(@RequestBody SimpleMailRequest simplemailRequest) {
		
		return "";
	}
	
	@PostMapping("/templatemessage")
	public String sendEmailWithTemplate(@RequestBody SimpleMailRequest simplemailRequest) {
		
		return "";
	}
	
	@PostMapping("/htmlmessage")
	public String sendEmailWithHtml(@RequestBody SimpleMailRequest simplemailRequest) {
		
		return "";
	}
	
	@GetMapping("/templates")
	public List<String> getTemplates() {
		
		return List.of("");
	}
}
