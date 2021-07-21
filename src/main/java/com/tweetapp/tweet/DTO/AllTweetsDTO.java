package com.tweetapp.tweet.DTO;

import java.util.List;

import com.tweetapp.tweet.model.Like;
import com.tweetapp.tweet.model.Reply;

public class AllTweetsDTO {
	
	private String id;
	private String emailId;
	private String firstName;
	private String lastName;

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
	private String postedTime;
	private String tweet;
	private List<Reply> reply;
	private List<Like> like;
	private long likeCount;
	private Boolean likeStatus;
	public AllTweetsDTO(String id, String emailId, String firstName, String lastName, String postedTime, String tweet,
			List<Reply> reply, List<Like> like, Boolean likeStatus, long likeCount) {
		super();
		this.id = id;
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.postedTime = postedTime;
		this.tweet = tweet;
		this.reply = reply;
		this.like = like;
		this.likeStatus = likeStatus;
		this.likeCount = likeCount;
	}
	public long getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
	}
	public Boolean getLikeStatus() {
		return likeStatus;
	}
	public void setLikeStatus(Boolean likeStatus) {
		this.likeStatus = likeStatus;
	}
	public AllTweetsDTO() {
		super();
	}
	@Override
	public String toString() {
		return "AllTweetsDTO [id=" + id + ", emailId=" + emailId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", postedTime=" + postedTime + ", tweet=" + tweet + ", reply=" + reply + ", like=" + like
				+ ", likeCount=" + likeCount + ", likeStatus=" + likeStatus + "]";
	}
	public String getId() {
		return id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((like == null) ? 0 : like.hashCode());
		result = prime * result + (int) (likeCount ^ (likeCount >>> 32));
		result = prime * result + ((likeStatus == null) ? 0 : likeStatus.hashCode());
		result = prime * result + ((postedTime == null) ? 0 : postedTime.hashCode());
		result = prime * result + ((reply == null) ? 0 : reply.hashCode());
		result = prime * result + ((tweet == null) ? 0 : tweet.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AllTweetsDTO other = (AllTweetsDTO) obj;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (like == null) {
			if (other.like != null)
				return false;
		} else if (!like.equals(other.like))
			return false;
		if (likeCount != other.likeCount)
			return false;
		if (likeStatus == null) {
			if (other.likeStatus != null)
				return false;
		} else if (!likeStatus.equals(other.likeStatus))
			return false;
		if (postedTime == null) {
			if (other.postedTime != null)
				return false;
		} else if (!postedTime.equals(other.postedTime))
			return false;
		if (reply == null) {
			if (other.reply != null)
				return false;
		} else if (!reply.equals(other.reply))
			return false;
		if (tweet == null) {
			if (other.tweet != null)
				return false;
		} else if (!tweet.equals(other.tweet))
			return false;
		return true;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public List<Reply> getReply() {
		return reply;
	}
	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}
	public List<Like> getLike() {
		return like;
	}
	public void setLike(List<Like> like) {
		this.like = like;
	}

}
