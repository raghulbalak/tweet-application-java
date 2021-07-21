package com.tweetapp.tweet.service;

import com.tweetapp.tweet.DTO.AllTweetsResponseDTO;
import com.tweetapp.tweet.DTO.RegisterResponseDTO;
import com.tweetapp.tweet.DTO.TweetResponseDTO;

public interface TweetService {
	
	TweetResponseDTO postTweet(String userId, String tweet) throws Exception;
	
	AllTweetsResponseDTO getAllTweets(String userId) throws Exception;
	
	RegisterResponseDTO updateTweet(String id, String tweet) throws Exception;
	
	AllTweetsResponseDTO getUserTweets(String userId) throws Exception;

	RegisterResponseDTO deleteTweet(String id) throws Exception;
	
	RegisterResponseDTO likeTweet(String userId, String id, Boolean status) throws Exception;
	
	RegisterResponseDTO replyTweet(String userId, String id, String reply) throws Exception;

}
