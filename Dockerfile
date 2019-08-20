FROM openjdk:8-jdk-alpine
LABEL MAINTAINER="Rashad Javadov <rashadjavad@gmail.com>"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=output/kstream-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app-demo.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app-demo.jar"]
