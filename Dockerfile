FROM openjdk:8-jdk-alpine
EXPOSE 8090
ADD target/tweet-0.0.1-SNAPSHOT.jar tweet-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","tweet-0.0.1-SNAPSHOT.jar"]
