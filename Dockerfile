FROM java:8
ADD target/tweet-0.0.1-SNAPSHOT.jar tweet-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","tweet.jar"]