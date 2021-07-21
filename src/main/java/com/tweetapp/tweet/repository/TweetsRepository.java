package com.tweetapp.tweet.repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tweetapp.tweet.model.Tweet;

@Repository
public class TweetsRepository {

	@Autowired
	private DynamoDBMapper dynamoMapper;

	public List<Tweet> findAll() {
		List<Tweet> result = dynamoMapper.scan(Tweet.class, new DynamoDBScanExpression());
		return result;
	}

	public List<Tweet> findByEmailId(String emailId) {
		List<Tweet> result = dynamoMapper.scan(Tweet.class, new DynamoDBScanExpression());
		List<Tweet> resultFilter = result.stream().filter(tweet -> tweet.getEmailId().contains(emailId)).collect(Collectors.toList());
		return resultFilter;
	}

	public Tweet findById(String id) {
		Tweet tweet = dynamoMapper.load(Tweet.class, id);
		return tweet;
	}

	public Tweet save(Tweet tweet) {
		dynamoMapper.save(tweet);
		return tweet;
	}

	public void deleteById(String id) {
		Tweet tweet = dynamoMapper.load(Tweet.class, id);
		dynamoMapper.delete(tweet);
	}

	public int getMaxTweetId() {
		List<Tweet> tweets = dynamoMapper.scan(Tweet.class, new DynamoDBScanExpression());
		List<Integer> tweetsId = tweets.stream().map(tweet -> Integer.parseInt(tweet.getId())).collect(Collectors.toList());
		return Collections.max(tweetsId);
	}

}
