FROM openjdk:8-jdk-alpine
LABEL MAINTAINER="Rashad Javadov <rashadjavad@gmail.com>"
VOLUME /tmp
EXPOSE 8080:8080
ARG JAR_FILE=./target/kstream-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} tmp/app-demo.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","tmp/app-demo.jar"]
