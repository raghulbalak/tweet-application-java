package com.tweetapp.tweet.DTO;

import com.tweetapp.tweet.model.ErrorMessage;

public class TweetResponseDTO {
	
	private Boolean status;
	private ErrorMessage error;
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public ErrorMessage getError() {
		return error;
	}
	public void setError(ErrorMessage error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "TweetResponseDTO [status=" + status + "]";
	}
	public TweetResponseDTO(Boolean status, ErrorMessage error) {
		super();
		this.status = status;
		this.error = error;
	}
	public TweetResponseDTO() {
		super();
	}
	
}
