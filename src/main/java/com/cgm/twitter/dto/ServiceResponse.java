package com.cgm.twitter.dto;

public class ServiceResponse {

	private int code = 200;
	private String message = "Operation succesfull!";
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
