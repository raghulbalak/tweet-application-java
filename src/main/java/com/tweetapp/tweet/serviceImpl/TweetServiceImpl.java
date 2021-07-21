package com.tweetapp.tweet.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.tweet.DTO.AllTweetsDTO;
import com.tweetapp.tweet.DTO.AllTweetsResponseDTO;
import com.tweetapp.tweet.DTO.RegisterResponseDTO;
import com.tweetapp.tweet.DTO.TweetResponseDTO;
import com.tweetapp.tweet.model.ErrorMessage;
import com.tweetapp.tweet.model.Like;
import com.tweetapp.tweet.model.Register;
import com.tweetapp.tweet.model.Reply;
import com.tweetapp.tweet.model.Tweet;
import com.tweetapp.tweet.repository.LoginRepository;
import com.tweetapp.tweet.repository.TweetsRepository;
import com.tweetapp.tweet.service.TweetService;

@Service
public class TweetServiceImpl implements TweetService {

	@Autowired
	TweetsRepository tweetsRepository;

	@Autowired
	LoginRepository loginRepo;

	public TweetResponseDTO postTweet(String userId, String tweet) throws Exception {
		Boolean status = false;
		TweetResponseDTO tweetResponse = new TweetResponseDTO();
		try {
			Register register = loginRepo.findByUserId(userId);
			if (register != null) {
				Tweet userTweet = new Tweet();
				userTweet.setId(String.valueOf(tweetsRepository.getMaxTweetId() + 1));
				userTweet.setTweet(tweet);
				userTweet.setEmailId(register.getEmailId());
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				userTweet.setPostedDate(dtf.format(now));
				userTweet.setLikes(null);
				userTweet.setReplies(null);
				tweetsRepository.save(userTweet);
				status = true;
				tweetResponse.setError(null);
			} else {
				status = false;
				tweetResponse.setError(new ErrorMessage(105, "No User found!"));
			}
		} catch (Exception e) {
			tweetResponse.setError(new ErrorMessage(106, "Unable to post tweet"));
			throw new Exception(e);
		}
		tweetResponse.setStatus(status);
		return tweetResponse;
	}

	public AllTweetsResponseDTO getAllTweets(String userId) throws Exception {
		AllTweetsResponseDTO allTweetsResponse = new AllTweetsResponseDTO();
		
		List<Tweet> tweets;
		List<AllTweetsDTO> allTweets = new ArrayList<AllTweetsDTO>();
		try {
			tweets = tweetsRepository.findAll();
			Register currentUser = loginRepo.findByUserId(userId);
			if(currentUser != null) {
			if (tweets != null) {
				tweets.stream().forEach((tweet) -> {
					AllTweetsDTO allTweet = new AllTweetsDTO();
					allTweet.setEmailId(tweet.getEmailId());

					Register user = loginRepo.findByEmailId(tweet.getEmailId());
					allTweet.setFirstName(user.getFirstName());
					allTweet.setLastName(user.getLastName());
					allTweet.setTweet(tweet.getTweet());
					allTweet.setId(tweet.getId());
					allTweet.setLike(tweet.getLikes());
					if(tweet.getLikes() != null) {
						allTweet.setLikeStatus(tweet.getLikes().contains(new Like(userId, currentUser.getFirstName(), true)));
						allTweet.setLikeCount(tweet.getLikes().size());
					} else {
						allTweet.setLikeStatus(false);
						allTweet.setLikeCount(0);
					}
					allTweet.setReply(tweet.getReplies());
					allTweet.setPostedTime(tweet.getPostedDate());
					allTweets.add(allTweet);
				});
				allTweetsResponse.setAllTweets(allTweets);
				allTweetsResponse.setError(null);

			} else {
				allTweetsResponse.setError(new ErrorMessage(103, "No tweets to be fetched"));
			}
		} else {
			allTweetsResponse.setError(new ErrorMessage(104, "Not a valid user id"));
		}
		} catch (Exception exception) {
			allTweetsResponse.setAllTweets(null);
			allTweetsResponse.setError(new ErrorMessage(108, "Unable to fetch all tweets"));
			throw new Exception(exception);
		}
		return allTweetsResponse;
	}

	public AllTweetsResponseDTO getUserTweets(String userId) throws Exception {
		AllTweetsResponseDTO allTweetsResponse = new AllTweetsResponseDTO();
		List<Tweet> tweets;
		List<AllTweetsDTO> allTweets = new ArrayList<AllTweetsDTO>();
		try {
			Register tweetEmailId = loginRepo.findByUserId(userId);
			if (tweetEmailId != null) {
				tweets = tweetsRepository.findByEmailId(tweetEmailId.getEmailId());
				if (tweets != null) {
					tweets.stream().forEach((tweet) -> {
						AllTweetsDTO allTweet = new AllTweetsDTO();
						allTweet.setEmailId(tweet.getEmailId());

						allTweet.setTweet(tweet.getTweet());
						allTweet.setFirstName(tweetEmailId.getFirstName());
						allTweet.setLastName(tweetEmailId.getLastName());
						if(tweet.getLikes() != null) {
							allTweet.setLikeStatus(tweet.getLikes().contains(new Like(userId, tweetEmailId.getFirstName(), true)));
							allTweet.setLikeCount(tweet.getLikes().size());
						} else {
							allTweet.setLikeStatus(false);
							allTweet.setLikeCount(0);
						}
						allTweet.setId(tweet.getId());
						allTweet.setLike(tweet.getLikes());
						allTweet.setReply(tweet.getReplies());
						allTweet.setPostedTime(tweet.getPostedDate());
						allTweets.add(allTweet);
					});
					allTweetsResponse.setAllTweets(allTweets);
					allTweetsResponse.setError(null);

				} else {
					allTweetsResponse.setAllTweets(null);
					allTweetsResponse.setError(new ErrorMessage(104, "No user found"));
				}
			} else {
				allTweetsResponse.setAllTweets(null);
				allTweetsResponse.setError(new ErrorMessage(104, "No user found"));
			}
		} catch (Exception exception) {
			allTweetsResponse.setAllTweets(null);
			allTweetsResponse.setError(new ErrorMessage(108, "Unable to fetch user tweets"));
			throw new Exception(exception);
		}
		return allTweetsResponse;
	}

	public RegisterResponseDTO updateTweet(String id, String tweet) throws Exception {
		RegisterResponseDTO updateTweet = new RegisterResponseDTO();
		try {
			Tweet update = tweetsRepository.findById(id);
			if (update != null) {
				update.setTweet(tweet);
				tweetsRepository.save(update);
				updateTweet.setStatus(true);
				updateTweet.setErrorMessage(null);
			} else {
				updateTweet.setStatus(false);
				updateTweet.setErrorMessage(new ErrorMessage(108, "Unable To Update Tweet"));
			}
		} catch (Exception exception) {
			updateTweet.setStatus(false);
			updateTweet.setErrorMessage(new ErrorMessage(108, "Unable To find Tweet"));
			throw new Exception("Unable to find tweet");
		}
		return updateTweet;
	}

	public RegisterResponseDTO deleteTweet(String id) throws Exception {
		RegisterResponseDTO deleteTweet = new RegisterResponseDTO();
		try {
			Tweet update = tweetsRepository.findById(id);
			if (update != null) {
				tweetsRepository.deleteById(id);
				deleteTweet.setStatus(true);
				deleteTweet.setErrorMessage(null);
			} else {
				deleteTweet.setStatus(false);
				deleteTweet.setErrorMessage(new ErrorMessage(109, "Unable To delete Tweet"));
			}
		} catch (Exception exception) {
			deleteTweet.setStatus(false);
			deleteTweet.setErrorMessage(new ErrorMessage(109, "Unable To find Tweet"));
			throw new Exception("Unable to find tweet");
		}
		return deleteTweet;
	}

	public RegisterResponseDTO replyTweet(String userId, String id, String reply) throws Exception {
		RegisterResponseDTO replied = new RegisterResponseDTO();
		try {
			Register user = loginRepo.findByUserId(userId);
			if (user != null) {
				Tweet tweet = tweetsRepository.findById(id);
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				if (tweet != null) {
					List<Reply> replies = tweet.getReplies();
					List<Reply> replyList = replies != null ? new ArrayList<Reply>(replies) : new ArrayList<Reply>();
					replyList.add(new Reply(userId, dtf.format(now), reply));
					tweet.setReplies(replyList);
					tweetsRepository.save(tweet);
					replied.setStatus(true);
					replied.setErrorMessage(null);
				} else {
					replied.setStatus(false);
					replied.setErrorMessage(new ErrorMessage(109, "Tweet not found"));
				}
			} else {
				replied.setStatus(false);
				replied.setErrorMessage(new ErrorMessage(109, "User not found"));
			}
		} catch (Exception exception) {
			replied.setStatus(false);
			replied.setErrorMessage(new ErrorMessage(110, "Unable to reply tweet"));
			throw new Exception(exception);
		}
		return replied;
	}

	public RegisterResponseDTO likeTweet(String userId, String id, Boolean status) throws Exception {
		RegisterResponseDTO liked = new RegisterResponseDTO();
		try {
			Register user = loginRepo.findByUserId(userId);
			if (user!= null) {
				Tweet tweet = tweetsRepository.findById(id);
				if (tweet != null) {
					List<Like> likes = tweet.getLikes();
					List<Like> likesList = likes != null ? new ArrayList<Like>(likes) : new ArrayList<Like>();
					if(status) {
					likesList.add(new Like(userId, user.getFirstName(), status));
					} else {
						likesList.remove(new Like(userId, user.getFirstName(), !status));
					}

					tweet.setLikes(likesList);
					tweetsRepository.save(tweet);
					liked.setStatus(true);
					liked.setErrorMessage(null);
				} else {
					liked.setStatus(false);
					liked.setErrorMessage(new ErrorMessage(109, "Tweet not found"));
				}
			} else {
				liked.setStatus(false);
				liked.setErrorMessage(new ErrorMessage(109, "User not found"));
			}
		} catch (Exception exception) {
			liked.setStatus(false);
			liked.setErrorMessage(new ErrorMessage(110, "Unable to like tweet"));
			throw new Exception(exception);
		}
		return liked;
	}

}
