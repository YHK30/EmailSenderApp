package com.emailapp.emailservice.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emailapp.emailservice.helper.CustomResponse;
import com.emailapp.emailservice.helper.EmailRequest;
import com.emailapp.emailservice.services.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	
	public EmailController() {
		super();
	}

	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@PostMapping("/send")
	public ResponseEntity<CustomResponse> sendEmail(@RequestBody EmailRequest request){
		emailService.sendEmailWithHtml(request.getTo(), request.getSubject(), request.getMessage());
		CustomResponse customResp = new CustomResponse();
		customResp.setMessage("Email Sent Successfully");
		customResp.setHttpStatus(HttpStatus.OK);
		customResp.setSuccess(true);
		return ResponseEntity.ok(customResp);
				
	}
	
	
	@PostMapping("/send-with-file") 
	public ResponseEntity<CustomResponse> sendWithFile(@RequestPart EmailRequest request, @RequestPart MultipartFile file){
		try { 
			emailService.sendEmailWithFile(request.getTo(), request.getSubject(),
		
	  request.getMessage(), file.getInputStream());
			} 
	catch (IOException e) {  
		 e.printStackTrace();
		 } 
		CustomResponse customResp = new CustomResponse();
	  customResp.setMessage("Email Sent Successfully");
	  customResp.setHttpStatus(HttpStatus.OK); customResp.setSuccess(true); 
	  return ResponseEntity.ok(customResp);
	
	  }}
	 
	
	/*
	@PostMapping("/send-with-file")
	 public String sendMailWithAttachment(@RequestBody EmailRequest request)
		    {
		        String status
		            = emailService.sendMailWithAttachment(request.getTo(), request.getSubject(), request);
		 
		        return status;
		    }
		    */
		//}
	
