package com.bitbank.exception;

import java.util.ArrayList;
import java.util.List;

import com.bitbank.model.BaseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class BusinessException extends Exception {

	private static final  Logger logger = LoggerFactory.getLogger(BusinessException.class);
	
	private static final long serialVersionUID = 1L;
	
	private List<FieldError> listFieldError;

	public BusinessException(String errorMessage) {
		super(errorMessage);
	
	}
	
	public BusinessException(String errorMessage, List<FieldError> listErrorField) {
		super(errorMessage);
		logger.error(errorMessage);
		listFieldError = listErrorField;
		System.out.println("*********************** ERRO DE CAMPOS INVÁLIDOS **************************************************");
		for (FieldError fieldError : listErrorField) {
			System.out.println(fieldError.getField()+" --> "+fieldError.getMessage());
		}
		System.out.println("***************************************************************************************************");
	} 
	
	public BusinessException(String errorMessage, List<FieldError> listErrorField, BaseModel entity) {
		super(errorMessage);
		listFieldError = listErrorField;
		System.out.println("*********************** ERRO DE CAMPOS INVÁLIDOS **************************************************");
		for (FieldError fieldError : listErrorField) {
			System.out.println(fieldError.getField()+" --> "+fieldError.getMessage());
		}
		System.out.println("***************************************************************************************************");
		entity.printValues();
	} 
	
	
	public void clearErrorFields() {
		listFieldError = null;
	}
	
	public void addErrorField(String field, String message) {
		if (listFieldError == null) {
			listFieldError = new ArrayList<FieldError>();
		}
		listFieldError.add(new FieldError(field, message));
	}

	public List<FieldError> getListFieldError() {
		return listFieldError;
	}
	
	public String getFieldErros() {
		if (listFieldError != null && !listFieldError.isEmpty()) {
			String tmp = "";
			for (FieldError fieldError : listFieldError) {
				tmp += " { "+fieldError.getField()+" : "+fieldError.getMessage()+" }\n";
			}
			return tmp;
		}
		return getMessage();
	}
}
