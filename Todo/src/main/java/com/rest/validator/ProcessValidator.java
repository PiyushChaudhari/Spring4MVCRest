package com.rest.validator;

import java.util.HashMap;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class ProcessValidator {

	public static HashMap<String, Object> getErrorsPaire(Errors errors) {
		int errorCount = 0;
		if (errors.hasErrors()) {
			errorCount = errors.getFieldErrors().size();
			HashMap<String, Object> errorMap = new HashMap<String, Object>(errorCount);
			for (FieldError fieldError: errors.getFieldErrors()) {
	            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
	        }
			return errorMap;
		} else {
			return new HashMap<String, Object>();
		}
		
	}

}
