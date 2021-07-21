package com.tweetapp.tweet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.tweetapp.tweet.DTO.AllTweetsResponseDTO;
import com.tweetapp.tweet.DTO.RegisterResponseDTO;
import com.tweetapp.tweet.DTO.TweetRequestDTO;
import com.tweetapp.tweet.DTO.TweetResponseDTO;
import com.tweetapp.tweet.service.TweetService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/${app.version}/tweets")
public class TweetsController {
	
	@Autowired
	TweetService tweetService;
	
	@ApiOperation(value = "Gets tweets of all users", response = AllTweetsResponseDTO.class, notes = "This API is used to get tweets of all users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping("/all/{userId}")
	public AllTweetsResponseDTO getAllTweets(@PathVariable String userId) throws Exception {
		AllTweetsResponseDTO tweets = tweetService.getAllTweets(userId);
		return tweets;
	}
	
	@ApiOperation(value = "Get Tweet of the user", response = AllTweetsResponseDTO.class, notes = "This API is used to get tweet of the user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping("/{userId}")
	public AllTweetsResponseDTO getUserTweets(@PathVariable String userId) throws Exception {
		AllTweetsResponseDTO tweets = tweetService.getUserTweets(userId);
		return tweets;
	}
	
	@ApiOperation(value = "Post Tweet from the user", response = TweetResponseDTO.class, notes = "This API is used to post tweet from the user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping("/{userId}/add")
	public ResponseEntity<TweetResponseDTO> postTweet(@PathVariable String userId, @RequestBody TweetRequestDTO tweet) throws Exception {
		TweetResponseDTO response = tweetService.postTweet(userId, tweet.getTweet());
		return ResponseEntity.ok().body(response);
	}
	
	@ApiOperation(value = "Updated the tweet", response = RegisterResponseDTO.class, notes = "This API is used to update the tweet")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PutMapping("/{userId}/update/{id}")
	public ResponseEntity<RegisterResponseDTO> updateTweet(@PathVariable String userId, @PathVariable String id, @RequestBody String tweet) throws Exception {
		RegisterResponseDTO update = tweetService.updateTweet(id, tweet);
		
		return ResponseEntity.ok().body(update);
	}
	
	@ApiOperation(value = "Deletes the tweet", response = RegisterResponseDTO.class, notes = "This API is used to delete the tweet")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/{userId}/delete/{id}")
	public ResponseEntity<RegisterResponseDTO> deleteTweet(@PathVariable String userId, @PathVariable String id) throws Exception {
		RegisterResponseDTO update = tweetService.deleteTweet(id);
		
		return ResponseEntity.ok().body(update);
	}
	
	@ApiOperation(value = "Reply to the tweet", response = RegisterResponseDTO.class, notes = "This API is used to reply to the tweet")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping("{userId}/reply/{id}")
	public ResponseEntity<RegisterResponseDTO> replyTweet(@PathVariable String userId, @PathVariable String id, @RequestBody String status) throws Exception {
		RegisterResponseDTO reply = tweetService.replyTweet(userId, id, status);
		return ResponseEntity.ok().body(reply);
	}
	
	@ApiOperation(value = "like the tweet", response = RegisterResponseDTO.class, notes = "This API is used to like the tweet")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PutMapping("{userId}/like/{id}")
	public ResponseEntity<RegisterResponseDTO> likeTweet(@PathVariable String userId, @PathVariable String id, @RequestBody Boolean status) throws Exception {
		RegisterResponseDTO like = tweetService.likeTweet(userId, id, status);
		return ResponseEntity.ok().body(like);
	}

}
