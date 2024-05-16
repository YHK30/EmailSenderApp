package com.emailapp.emailservice.services.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.emailapp.emailservice.helper.EmailRequest;
import com.emailapp.emailservice.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	private Logger logger= LoggerFactory.getLogger(EmailServiceImpl.class);
	
	//constructor
	public EmailServiceImpl(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}

	@Override
	public void sendEmail(String to, String subject, String message) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("yhkadam9@gmail.com");
		mailSender.send(simpleMailMessage);
		logger.info("Email has been sent...");
		
	}

	@Override
	public void sendEmail(String[] to, String subject, String message) {
		 for (String recipient : to) {
		        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		        simpleMailMessage.setTo(recipient);
		        simpleMailMessage.setSubject(subject);
		        simpleMailMessage.setText(message);
		        simpleMailMessage.setFrom("yhkadam9@gmail.com");

		        // Send the email for each recipient
		        try {
		            mailSender.send(simpleMailMessage);
		            logger.info("Email sent to: " + recipient);
		        } catch (MailException e) {
		            logger.error("Failed to send email to: " + recipient, e);
		            // Handle the exception, e.g., log the error or throw it further
		        }
		    }
		    logger.info("All emails have been sent...");
		
		
	}

	@Override
	public void sendEmailWithHtml(String to, String subject, String htmlContent) {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper= new MimeMessageHelper(mimeMessage, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("yhkadam9@gmail.com");
			helper.setText(htmlContent, true);
			mailSender.send(mimeMessage);
			logger.info("Email has been sent...");
		
		
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mailSender.send(mimeMessage);
		
	}

	@Override
	public void sendEmailWithFile(String to, String subject, String message, File file) {
		
		MimeMessage mimeMessage= mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper= new MimeMessageHelper(mimeMessage, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("yhkadam9@gmail.com");
			helper.setText(message);
			
			FileSystemResource fileSystemResource= new FileSystemResource(file);
			
			helper.addAttachment(fileSystemResource.getFilename(), file);
			mailSender.send(mimeMessage);
			logger.info("Email has been sent...");
		
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	public void sendEmailWithFile(String to, String subject, String message, InputStream inputStream) {
		
MimeMessage mimeMessage= mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper= new MimeMessageHelper(mimeMessage, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("yhkadam9@gmail.com");
			helper.setText(message);
			
			File file= new File("yashkadam.jpg");
			Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			FileSystemResource fileSystemResource= new FileSystemResource(file);
			
			helper.addAttachment(fileSystemResource.getFilename(), file);
			mailSender.send(mimeMessage);
			logger.info("Email has been sent...");
		
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	/*
	@Override
    public String
    sendMailWithAttachment(String to, String subject, EmailRequest request)
    {
        // Creating a mime message
        MimeMessage mimeMessage
            = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
 
        try {
 
            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("yhkadam9@gmail.com");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(request.getMessage());
            mimeMessageHelper.setSubject(subject);
 
            // Adding the attachment
            FileSystemResource file
                = new FileSystemResource(
                    new File(request.getAttachment()));
 
            mimeMessageHelper.addAttachment(
                file.getFilename(), file);
 
            // Sending the mail
            mailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }
 
        // Catch block to handle MessagingException
        catch (MessagingException e) {
 
            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
    */
	
}
