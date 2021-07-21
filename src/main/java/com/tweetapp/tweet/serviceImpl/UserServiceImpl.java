package com.tweetapp.tweet.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.tweet.DTO.RegisterResponseDTO;
import com.tweetapp.tweet.DTO.UserDTO;
import com.tweetapp.tweet.DTO.UserResponseDTO;
import com.tweetapp.tweet.model.ErrorMessage;
import com.tweetapp.tweet.model.Register;
import com.tweetapp.tweet.repository.LoginRepository;
import com.tweetapp.tweet.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	LoginRepository login;

	@Override
	public UserResponseDTO getAllUsers() throws Exception {
		UserResponseDTO userResponse = new UserResponseDTO();
		List<UserDTO> users = new ArrayList<UserDTO>();
		try {
			List<Register> allUsers = login.findAll();
			allUsers.stream().forEach((user) -> {
				UserDTO userDTO = new UserDTO();
				userDTO.setEmailId(user.getEmailId());
				userDTO.setFirstName(user.getFirstName());
				userDTO.setLastName(user.getLastName());
				users.add(userDTO);
			});
			userResponse.setUsers(users);
			userResponse.setError(null);
		} catch (Exception e) {
			userResponse.setUsers(null);
			userResponse.setError(new ErrorMessage(107, "Error fetching all users"));
			throw new Exception("Error fetching all users");
		}
		return userResponse;
	}
	
	@Override
	public RegisterResponseDTO checkUser(String userId) throws Exception {
		RegisterResponseDTO check = new RegisterResponseDTO();
		Boolean status =false;
		try {
			if(login.findByUserId(userId) != null) {
				status = true;
			} else {
				check.setErrorMessage(new ErrorMessage(102, "No user found"));
			}
		} catch (Exception exception) {
			check.setErrorMessage(new ErrorMessage(102, "Error connecting with the database"));
		}
		check.setStatus(status);
		return check;
	}

}
