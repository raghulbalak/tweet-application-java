package com.tweetapp.tweet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.tweet.DTO.AllTweetsResponseDTO;
import com.tweetapp.tweet.DTO.RegisterResponseDTO;
import com.tweetapp.tweet.DTO.UserResponseDTO;
import com.tweetapp.tweet.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/${app.version}/tweets")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@ApiOperation(value = "Gets all users", response = AllTweetsResponseDTO.class, notes = "This API is used to get all the users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping("/users/all")
	public ResponseEntity<UserResponseDTO> getAllUsers() throws Exception {
		UserResponseDTO users = userService.getAllUsers();
		return ResponseEntity.ok().body(users);
	}
	
	@ApiOperation(value = "to check the user exist", response = AllTweetsResponseDTO.class, notes = "This API is used to check the user exist")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping("/users/{userId}")
	public ResponseEntity<RegisterResponseDTO> checkUser(@PathVariable String userId) throws Exception {
		RegisterResponseDTO user = userService.checkUser(userId);
		return ResponseEntity.ok().body(user);
	}

}
