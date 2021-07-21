package com.tweetapp.tweet.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.tweetapp.tweet.converter.LikesConverter;
import com.tweetapp.tweet.converter.RepliesConverter;

@DynamoDBTable(tableName="Tweets")
public class Tweet {
	
	private String id;
	
	@Override
	public String toString() {
		return "Tweet [id=" + id + ", emailId=" + emailId + ", tweet=" + tweet + ", postedDate=" + postedDate
				+ ", replies=" + replies + ", likes=" + likes + "]";
	}

	private String emailId;

	private String tweet;
	

	private String postedDate;

	private List<Reply> replies;

	private List<Like> likes;
	
	public Tweet(String id, String tweet, String postedDate, List<Reply> replies, List<Like> likes,  String emailId) {
		super();
		this.id = id;
		this.tweet = tweet;
		this.postedDate = postedDate;
		this.replies = replies;
		this.likes = likes;
		this.emailId = emailId;
	}
	
	@DynamoDBAttribute(attributeName="emailId")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@DynamoDBTypeConverted(converter = RepliesConverter.class)
	@DynamoDBAttribute(attributeName="replies")
	public List<Reply> getReplies() {
		return replies;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	
	@DynamoDBTypeConverted(converter = LikesConverter.class)
	@DynamoDBAttribute(attributeName="likes")
	public List<Like> getLikes() {
		return likes;
	}
	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}
	
	@Id
	@DynamoDBHashKey(attributeName="id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@DynamoDBAttribute(attributeName="tweet")
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	@DynamoDBAttribute(attributeName="postedDate")
	public String getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}
	
	public Tweet() {
		super();
	}
	
	

}
