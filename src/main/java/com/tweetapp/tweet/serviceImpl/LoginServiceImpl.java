package com.tweetapp.tweet.serviceImpl;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.tweet.DTO.LoginRequestDTO;
import com.tweetapp.tweet.DTO.LoginResponseDTO;
import com.tweetapp.tweet.DTO.RegisterRequestDTO;
import com.tweetapp.tweet.DTO.RegisterResponseDTO;
import com.tweetapp.tweet.model.ErrorMessage;
import com.tweetapp.tweet.model.Register;
import com.tweetapp.tweet.repository.LoginRepository;
import com.tweetapp.tweet.service.LoginService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginRepository loginRepository;

	@Override
	public LoginResponseDTO login(LoginRequestDTO loginRequest) throws Exception {
		LoginResponseDTO loginResponse = new LoginResponseDTO();
		try {
			Register register = loginRepository.findByUserId(loginRequest.getUserId());
			if (register != null && PasswordGenerationHash.validate(loginRequest.getPassword(), register.getPassword())) {
				Register registerObject = register;
				loginResponse.setUserId(loginRequest.getUserId());
				loginResponse.setEmailId(registerObject.getEmailId());
				loginResponse.setToken("Bearer" + getJWTToken(loginRequest));
				loginResponse.setErrorMessage(null);
			} else {
				loginResponse.setErrorMessage(new ErrorMessage(101, "Invalid Credentials"));
			}
		} catch (Exception e) {
			loginResponse.setErrorMessage(new ErrorMessage(102, "Unable to connect to database"));
			e.printStackTrace();
		}
		return loginResponse;
	}

	private String getJWTToken(LoginRequestDTO loginRequest) {
		String secret = "key";
		String token = Jwts.builder().setId("FSEJWT").setSubject(loginRequest.getUserId())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 60000))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();

		return token;
	}

	@Override
	public RegisterResponseDTO registerUser(RegisterRequestDTO registerDTO) throws Exception {
		RegisterResponseDTO registerResponse = new RegisterResponseDTO();
		boolean status = true;
		try {
			if (loginRepository.findByEmailId(registerDTO.getEmailId()) != null) {
				status = false;
				ErrorMessage error = new ErrorMessage(101, "Email Id already exists!");
				registerResponse.setErrorMessage(error);
			}
			if (loginRepository.findByUserId(registerDTO.getUserId()) != null) {
				status = false;
				if (registerResponse.getErrorMessage() != null) {
					ErrorMessage error = new ErrorMessage(103, "Email Id and User Id already exists!");
					registerResponse.setErrorMessage(error);
				} else {
					ErrorMessage error = new ErrorMessage(102, "User Id already exists!");
					registerResponse.setErrorMessage(error);
				}
			}
			if (status) {
				loginRepository.save(convertFrom(registerDTO));
				registerResponse.setErrorMessage(null);
			}

		} catch (Exception e) {
			status = false;
			throw new Exception("Registration unsuccessful registerUser");
		}
		registerResponse.setStatus(status);
		return registerResponse;
	}

	Register convertFrom(RegisterRequestDTO registerDTO) throws NoSuchAlgorithmException {
		Register register = new Register();
		register.setId(String.valueOf(loginRepository.getMaxId() + 1));
		register.setContactNumber(registerDTO.getContactNumber());
		register.setEmailId(registerDTO.getEmailId());
		register.setFirstName(registerDTO.getFirstName());
		register.setLastName(registerDTO.getLastName());
		String saltAdded = PasswordGenerationHash.getSalt();
		register.setPassword(PasswordGenerationHash.get_SHA_384_SecurePassword(registerDTO.getPassword(), saltAdded));
		register.setUserId(registerDTO.getUserId());

		return register;
	}

	public RegisterResponseDTO forgotPassword(String userId, String password) throws Exception {
		RegisterResponseDTO forgot = new RegisterResponseDTO();
		Boolean status = true;
		try {
			Register register = loginRepository.findByUserId(userId);
			if (register != null) {
				String saltValue = PasswordGenerationHash.getSalt();
				register.setPassword(PasswordGenerationHash.get_SHA_384_SecurePassword(password, saltValue));

				loginRepository.save(register);
				forgot.setErrorMessage(null);
			} else {
				status = false;
				forgot.setErrorMessage(new ErrorMessage(104, "No User Id found"));
			}
		} catch (Exception e) {
			status = false;
			throw new Exception();
		}
		forgot.setStatus(status);
		return forgot;
	}

}
