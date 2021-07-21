package com.tweetapp.tweet.service;

import com.tweetapp.tweet.DTO.RegisterResponseDTO;
import com.tweetapp.tweet.DTO.UserResponseDTO;

public interface UserService {
	
	UserResponseDTO getAllUsers() throws Exception;
	
	RegisterResponseDTO checkUser(String userId) throws Exception;

}
