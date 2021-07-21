package com.tweetapp.tweet.DTO;

import java.util.List;

import com.tweetapp.tweet.model.ErrorMessage;

public class UserResponseDTO {
	
	private List<UserDTO> users;
	private ErrorMessage error;
	public List<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	public ErrorMessage getError() {
		return error;
	}
	public void setError(ErrorMessage error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "UserResponseDTO [users=" + users + ", error=" + error + "]";
	}
	public UserResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserResponseDTO(List<UserDTO> users, ErrorMessage error) {
		super();
		this.users = users;
		this.error = error;
	}

}
