package com.tweetapp.tweet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class DynamoDBConfig {
 
	@Value("${​​​​​amazon.aws.accesskey}​​​​​")
	private String ACCESS_KEY;

	 
	@Value("${​​​​​amazon.aws.secretkey}​​​​​")
	private String SECRET_KEY;

	 
	@Value("${​​​​​amazon.aws.region}​​​​​")
	private String REGION;

	 
	@Value("${​​​​​amazon.dynamodb.endpoint}​​​​​")
	private String SERVICE_ENDPOINT;


	@Bean
	public DynamoDBMapper mapper() {
		return new DynamoDBMapper(amazonDynamoDBConfig());
	}

	private AmazonDynamoDB amazonDynamoDBConfig() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(SERVICE_ENDPOINT, REGION))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
				.build();
	}
}
