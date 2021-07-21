package com.tweetapp.tweet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.tweet.DTO.ForgotPasswordRequestDTO;
import com.tweetapp.tweet.DTO.LoginRequestDTO;
import com.tweetapp.tweet.DTO.LoginResponseDTO;
import com.tweetapp.tweet.DTO.RegisterResponseDTO;
import com.tweetapp.tweet.service.LoginService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/${app.version}/tweets")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@ApiOperation(value = "Registers the user", response = LoginResponseDTO.class, notes = "This API is used to register the user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) throws Exception {
		LoginResponseDTO loginResponseDTO = loginService.login(loginRequest);
		return ResponseEntity.ok().body(loginResponseDTO);
	}
	
	@ApiOperation(value = "Reset the password", response = RegisterResponseDTO.class, notes = "This API is used to reset the user password")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PutMapping("/{userId}/forgot")
	public ResponseEntity<RegisterResponseDTO> forgotPassword(@PathVariable("userId") String userId,
			@RequestBody ForgotPasswordRequestDTO forgotPassword) throws Exception {
		RegisterResponseDTO forgot = loginService.forgotPassword(userId, forgotPassword.getPassword());
		return ResponseEntity.ok().body(forgot);
		
	}

}
