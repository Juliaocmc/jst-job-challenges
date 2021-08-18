package com.bitbank.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.bitbank.exception.BusinessException;
import com.bitbank.exception.FieldError;



public abstract class BaseModel implements Serializable {
	
	public void validate() throws BusinessException {
		List<FieldError> listFieldError = new ArrayList<FieldError>();
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<BaseModel>> constraintViolations = validator.validate(this);
		
		for (Iterator<ConstraintViolation<BaseModel>> iterator = constraintViolations.iterator(); iterator.hasNext();) {
			ConstraintViolation<BaseModel> constraintViolation = (ConstraintViolation<BaseModel>) iterator.next();					
			listFieldError.add(new FieldError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()));
		}
		
		if (listFieldError != null && listFieldError.size() > 0) {
			throw new BusinessException("Campos inválidos", listFieldError);
		}
	}
	
		
	public void printValues() {
		Field [] attributes =  this.getClass().getDeclaredFields();
		System.out.println("========================= [Valores da entidade " + this.getClass().getSimpleName()+"]=====================================");
	    for (Field field : attributes) {
	        // Dynamically read Attribute Name
//	        System.out.println("ATTRIBUTE NAME: " + field.getName());
	    	try {
    			Method m = this.getClass().getMethod("get"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1));
				System.out.println(field.getName()+" --> "+m.invoke(this));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
//				e.printStackTrace();
			}
	    }
	    System.out.println("===================================================================================");
	}
}
