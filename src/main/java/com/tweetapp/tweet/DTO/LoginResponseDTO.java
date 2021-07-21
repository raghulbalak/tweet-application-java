package com.tweetapp.tweet.DTO;

import com.tweetapp.tweet.model.ErrorMessage;

public class LoginResponseDTO {

	private String userId;
	
	private String emailId;

	private ErrorMessage errorMessage;

	private String token;

	public LoginResponseDTO() {
		super();
	}

	public LoginResponseDTO(String userId, String emailId, ErrorMessage errorMessage, String token) {
		super();
		this.userId = userId;
		this.emailId = emailId;
		this.errorMessage = errorMessage;
		this.token = token;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "LoginResponseDTO [userId=" + userId + ", emailId=" + emailId + ", errorMessage=" + errorMessage
				+ ", token=" + token + "]";
	}

}
