package com.bartosztanski.mailservice.starter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
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

		//emailService.sendSimpleMessage("b.t4nsky@gmail.com", "Spring boot test", "Message sended successfully");
		
		Path filePath = new File(getClass().getResource("/test.html").getFile()).toPath(); 

        Charset charset = StandardCharsets.UTF_8;

        StringBuilder stringBuilder = new StringBuilder();
        
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath, charset)) {
            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
        
        File file = new File(getClass().getResource("/mail-icon.png").getFile());
        
		emailService.sendMessageWithHTML("b.t4nsky@gmail.com", "Spring boot test",
				stringBuilder.toString(),file);
		
	}

}
