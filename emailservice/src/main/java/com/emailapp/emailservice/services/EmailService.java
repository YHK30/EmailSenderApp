package com.emailapp.emailservice.services;

import java.io.File;
import java.io.InputStream;

import com.emailapp.emailservice.helper.CustomResponse;
import com.emailapp.emailservice.helper.EmailRequest;

public interface EmailService {
	
	//send email to single person
	void sendEmail(String to, String subject, String message);
	
	//send email to multiple person
	void sendEmail(String []to, String subject, String message);
	
	//send email with html content
	void sendEmailWithHtml(String to, String subject, String htmlContent);
	
	//send email with file
	void sendEmailWithFile(String to, String subject, String message, File file);

	//String sendMailWithAttachment(String to, String subject, EmailRequest request);

	void sendEmailWithFile(String to, String subject, String message, InputStream inputStream);

}
