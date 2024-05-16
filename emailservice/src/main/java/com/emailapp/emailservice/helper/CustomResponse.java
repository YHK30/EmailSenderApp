package com.emailapp.emailservice.helper;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonValue;

public class CustomResponse {

	private String message;
	private HttpStatus httpStatus;
	private boolean success = false;

	public CustomResponse(String message, HttpStatus httpStatus, boolean success) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.success = success;
	}

	public CustomResponse() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@JsonGetter("httpStatus")
	public String getHttpStatus() {
		return this.httpStatus != null ? this.httpStatus.name() : null;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
