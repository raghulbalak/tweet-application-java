package com.tweetapp.tweet.DTO;

import com.tweetapp.tweet.model.ErrorMessage;

public class RegisterResponseDTO {
	
	private Boolean status;
	
	private ErrorMessage errorMessage;

	@Override
	public String toString() {
		return "RegisterResponseDTO [status=" + status + "]";
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

}
