package com.tweetapp.tweet.converter;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.tweetapp.tweet.model.Like;

public class LikesConverter  implements
	DynamoDBTypeConverter<String, List<Like>> {

@Override
public String convert(List<Like> likes) {
    String likesResult = "_";
    try {
    	if (likes != null) {
    		for(int i=0; i<likes.size(); i++) {
    			String likesArray = String.format("%s*%s*%s", likes.get(i).getUserId(), likes.get(i).getLike(),
            			likes.get(i).getFirstName());
            
        		likesResult = String.format("%s^%s", likesResult, likesArray);
    		}
    	}
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    return likesResult;
}

@Override
public List<Like> unconvert(String object) {
	List<Like> likes = new ArrayList<Like>();
    try {
        if (object != null && object.length() != 1) {
        	String[] dataLike = object.replace("^", " ").split(" "); // ["123$123$123", "15$15"]
        	for(int i=0; i<dataLike.length; i++) {
        		if(dataLike[i].trim().length() != 1) {
        		String[] data = dataLike[i].replace("*", " ").split(" "); // ["123", "123", "123"]
        			Like like = new Like();
                    like.setUserId(data[0].trim());
                    like.setLike(Boolean.parseBoolean(data[1].trim()));
                    like.setFirstName(data[2].trim());
                    likes.add(like);
        	}
        	}
        }
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    return likes;
}

}
