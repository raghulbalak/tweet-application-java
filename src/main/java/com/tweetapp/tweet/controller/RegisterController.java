package com.tweetapp.tweet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.tweet.DTO.LoginResponseDTO;
import com.tweetapp.tweet.DTO.RegisterRequestDTO;
import com.tweetapp.tweet.DTO.RegisterResponseDTO;
import com.tweetapp.tweet.service.LoginService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/${app.version}/tweets")
public class RegisterController {
	
	@Autowired
	LoginService loginService;
	
	@ApiOperation(value = "Logins the user", response = RegisterResponseDTO.class, notes = "This API is used to login the user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO registerDTO) throws Exception {
		RegisterResponseDTO response = loginService.registerUser(registerDTO);
		return ResponseEntity.ok().body(response);
	}

}
