FROM openjdk:8-jdk-alpine
MAINTAINER "Rashad Javadov"
VOLUME /tmp
EXPOSE 8080:8080
COPY kstream-0.0.1-SNAPSHOT.jar tmp/app.jar
CMD ["java","-jar","tmp/app.jar"]