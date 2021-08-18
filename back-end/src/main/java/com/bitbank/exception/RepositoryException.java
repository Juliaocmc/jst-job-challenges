package com.bitbank.exception;


public class RepositoryException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public RepositoryException(String errorMessage) {
		super(errorMessage);
	}
}
