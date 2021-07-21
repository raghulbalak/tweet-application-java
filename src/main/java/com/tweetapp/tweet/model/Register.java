package com.tweetapp.tweet.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Users")
public class Register implements Serializable {
	@Id
	@DynamoDBHashKey(attributeName="id")
	private String id;
	
	@DynamoDBAttribute(attributeName="firstName")
	private String firstName;
	
	@DynamoDBAttribute(attributeName="lastName")
	private String lastName;
	
	@DynamoDBAttribute(attributeName="emailId")
	private String emailId;
	
	@DynamoDBAttribute(attributeName="userId")
	private String userId;
	
	@DynamoDBAttribute(attributeName="password")
	private String password;
	
	@DynamoDBAttribute(attributeName="contactNumber")
	private String contactNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "Register [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", userId=" + userId + ", password=" + password + ", contactNumber=" + contactNumber + "]";
	}
	
	public Register() {}

	public Register(String id, String firstName, String lastName, String emailId, String userId, String password,
			String contactNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.userId = userId;
		this.password = password;
		this.contactNumber = contactNumber;
	}

}
