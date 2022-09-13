package com.bramfitt.busarrival.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleException(ResourceNotFoundException e) {
    	LOG.info("Calling handleException");

    	List<String> details = new ArrayList<String>();
    	details.add(e.getMessage());    

    	ErrorMessage errMsg = new ErrorMessage(new Date(), "Server Error", details);
       
    	return new ResponseEntity<Object>(errMsg, HttpStatus.NO_CONTENT);
    }  
   
}
