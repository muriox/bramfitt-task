package com.bramfitt.busarrival.exception;

import java.util.Date;
import java.util.List;

public class ErrorMessage {

	private Date timestamp;
	private String message;
	private List<String> description;

	public ErrorMessage(Date timestamp, String message, List<String> description) {
	    this.timestamp = timestamp;
	    this.message = message;
	    this.description = description;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDescription() {
		return description;
	}

	public void setDescription(List<String> description) {
		this.description = description;
	}
	  
	  
}
