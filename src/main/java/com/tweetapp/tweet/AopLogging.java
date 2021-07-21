package com.tweetapp.tweet;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AopLogging { 
	
	private static final Logger LOG = LoggerFactory.getLogger(AopLogging.class);
	
	@Around("execution (* com.tweetapp.tweet.*.*.*(..))")
	public Object logDataApp(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
		Object result = null;LOG.info("Entering in the method:" + joinPoint.getSignature().getDeclaringTypeName() + 
				joinPoint.getSignature().getName() +
				Arrays.toString(joinPoint.getArgs()));
		result = joinPoint.proceed();
		
		LOG.info("Exiting from the method:" + joinPoint.getSignature().getDeclaringTypeName() + 
				joinPoint.getSignature().getName() +
				Arrays.toString(joinPoint.getArgs()));
		
		if(result != null) {
			LOG.debug("Method returned value is :" + result.toString());
		}return result;
		} catch(Exception e) {
			e.printStackTrace();
			LOG.error("Error in method:" + e);
			throw e;
		}
	}

}
