package com.backlink.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.backlink.Message.MessageException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7871652598123150450L;
	private String resourceName;
    private String fieldName;
    private Object fieldValue;
    
    public ResourceNotFoundException( String resourceName, String fieldName, Object fieldValue) {
        super(String.format(MessageException.CUSTOM_NOT_FOUND, resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}