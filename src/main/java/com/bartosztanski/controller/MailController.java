package com.bartosztanski.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bartosztanski.model.MailRequest;

@RestController
@RequestMapping("/mail/")
public class MailController {
	
	@PostMapping("/send")
	public String sendEmail(@RequestBody MailRequest mailRequest) {
		
		return "";
	}
	
}
