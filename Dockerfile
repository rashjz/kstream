FROM openjdk:8-jdk-alpine
MAINTAINER "Rashad Javadov"
VOLUME /tmp
EXPOSE 8080:8080
ADD target/kstream-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java","-jar","/app.jar"]