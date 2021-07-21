package com.tweetapp.tweet.model;

public class Reply {

	private String userId;

	private String date;

	private String reply;

	public Reply(String userId, String date, String reply) {
		super();
		this.userId = userId;
		this.date = date;
		this.reply = reply;
	}

	public Reply() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "Reply [userId=" + userId + ", date=" + date + ", reply=" + reply + "]";
	}

}
