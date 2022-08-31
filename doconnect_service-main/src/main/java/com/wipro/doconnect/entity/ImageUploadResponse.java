package com.wipro.doconnect.entity;
/*
* @Author : sourabh
* Modified last date: 30-08-22
* Description :ImageUploadResponse Entity
*/
public class ImageUploadResponse {
	
	 private String message;

	    public ImageUploadResponse(String message) {
	        this.message = message;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

}
