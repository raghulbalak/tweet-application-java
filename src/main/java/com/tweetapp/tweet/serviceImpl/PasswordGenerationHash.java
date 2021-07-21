package com.tweetapp.tweet.serviceImpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordGenerationHash {
	
	private static final int bytesLength = 16;
	
	protected static String get_SHA_384_SecurePassword(String passwordToHash, String salt) throws NoSuchAlgorithmException
    {
        String generatedPassword = null;
        String encryptedPassword = null;
        byte[] saltConverted = salt.getBytes();
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            md.update(saltConverted);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            encryptedPassword = sb.toString();
            
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        generatedPassword = Integer.toString(salt.toString().length()) + salt.toString() + encryptedPassword;
        return generatedPassword;
    }
     
    //Add salt
    protected static String getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[bytesLength];
        sr.nextBytes(salt);
        return salt.toString();
    }
    
   protected static Boolean validate(String password, String dbPassword) throws NoSuchAlgorithmException {
	   int lengthOfSalt = Integer.parseInt(dbPassword.substring(0, 2));
	   Boolean passwordCheck = false;
	   String salt = dbPassword.substring(2, lengthOfSalt + 2);
	   if(dbPassword.equals(get_SHA_384_SecurePassword(password, salt))) {
		   passwordCheck = true;
	   }

	   return passwordCheck;
   }

}
