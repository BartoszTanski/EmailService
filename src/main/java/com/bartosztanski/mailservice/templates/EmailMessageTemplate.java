package com.bartosztanski.mailservice.templates;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailMessageTemplate {
	
	/**
	* Returns an SimpleMailMessage object with text set as: 
	* "This is the test email template for your email:"+testText
	* <p> 
	* @paramtestText is a String that will be added to template
	* @return      SimpleMailMessage 
	* @see         SimpleMailMessage
	*/
	public SimpleMailMessage getTestTemplate(String testText) {
	    SimpleMailMessage message = new SimpleMailMessage();
	    String text = String.format("This is the test email template for your email:\n%s\n",testText); 
	    message.setText(text);
	    return message;
	}

}
