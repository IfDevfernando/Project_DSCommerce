package com.donbusiness.business.DTO;

public class FieldMessage {
	
	private String fielddName;
	private String message;
	public FieldMessage(String fielddName, String message) {
		super();
		this.fielddName = fielddName;
		this.message = message;
	}
	public String getFielddName() {
		return fielddName;
	}
	public String getMessage() {
		return message;
	}

	
}
