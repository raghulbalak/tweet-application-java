package com.tweetapp.tweet.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.tweetapp.tweet.DTO.LoginRequestDTO;
import com.tweetapp.tweet.DTO.LoginResponseDTO;
import com.tweetapp.tweet.DTO.RegisterRequestDTO;
import com.tweetapp.tweet.DTO.RegisterResponseDTO;

public interface LoginService {
	LoginResponseDTO login(LoginRequestDTO loginRequest) throws Exception;

	RegisterResponseDTO registerUser(RegisterRequestDTO registerDTO) throws Exception;
	
	RegisterResponseDTO forgotPassword(String userName, String password) throws Exception;

}
