FROM openjdk:8-jdk-alpine
LABEL MAINTAINER="Rashad Javadov <rashadjavad@gmail.com>"
VOLUME /tmp
WORKDIR /tmp
EXPOSE 8080
ADD build-jar.sh /tmp/build-jar.sh
RUN /tmp/build-jar.sh
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/tmp/kstream-master/target/kstream-0.0.1-SNAPSHOT.jar"]
