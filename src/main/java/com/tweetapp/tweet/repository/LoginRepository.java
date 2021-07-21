package com.tweetapp.tweet.repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tweetapp.tweet.model.Register;
import com.tweetapp.tweet.model.Tweet;

@Repository
public class LoginRepository {
	
	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	
	public Register findByEmailId(String emailId) {
		List<Register> result = dynamoDBMapper.scan(Register.class, new DynamoDBScanExpression());
		List<Register> resultFilter = result.stream().filter(user -> user.getEmailId().contains(emailId)).collect(Collectors.toList());
		if(resultFilter.size() == 0) {
			return null;
		} else {
			return resultFilter.get(0);
		}
	}
	
	public List<Register> findAll() {
		List<Register> result = dynamoDBMapper.scan(Register.class, new DynamoDBScanExpression());
		return result;
	}
	
	public Register save(Register user) {
		dynamoDBMapper.save(user);
		return user;
	}

	public Register findByUserId(String userId) {
		List<Register> result = dynamoDBMapper.scan(Register.class, new DynamoDBScanExpression());
		List<Register> resultFilter = result.stream().filter(user -> user.getUserId().equals(userId)).collect(Collectors.toList());
		if(resultFilter.size() == 0) {
			return null;
		} else {
			return resultFilter.get(0);
		}
	}
	
	public int getMaxId() {
		List<Register> result = dynamoDBMapper.scan(Register.class, new DynamoDBScanExpression());
		List<Integer> resultId = result.stream().map(user -> Integer.parseInt(user.getId())).collect(Collectors.toList());
		return Collections.max(resultId);
	}

}
