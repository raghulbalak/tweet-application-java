package com.tweetapp.tweet.DTO;

import java.util.List;

import com.tweetapp.tweet.model.ErrorMessage;

public class AllTweetsResponseDTO {
	
	private List<AllTweetsDTO> allTweets;
	private ErrorMessage error;
	public AllTweetsResponseDTO(List<AllTweetsDTO> allTweets, ErrorMessage error) {
		super();
		this.allTweets = allTweets;
		this.error = error;
	}
	public List<AllTweetsDTO> getAllTweets() {
		return allTweets;
	}
	public void setAllTweets(List<AllTweetsDTO> allTweets) {
		this.allTweets = allTweets;
	}
	public ErrorMessage getError() {
		return error;
	}
	public void setError(ErrorMessage error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "AllTweetsResponseDTO [allTweets=" + allTweets + ", error=" + error + "]";
	}
	public AllTweetsResponseDTO() {
		super();
	}

}
