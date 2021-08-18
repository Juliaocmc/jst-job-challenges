package com.bitbank.exception;

public class FieldError {
	private String field;
	private String message;
	
	public FieldError(String field, String message) {
		this.field = field;
		this.message = message;
	}
	
	public FieldError() {
		
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
