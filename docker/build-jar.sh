#!/bin/sh
echo setup maven
apk add --update maven > /dev/null 2>&1
apk add --update  unzip > /dev/null 2>&1
echo getting git project
wget -P /tmp/ 'https://github.com/rashjz/kstream/archive/master.zip' && \
unzip /tmp/master.zip -d /tmp/
echo building project
mvn package -f /tmp/kstream-master/pom.xml > /dev/null 2>&1
chmod 777 /tmp/kstream-master/target/kstream-0.0.1-SNAPSHOT.jar
mv /tmp/kstream-master/target/kstream-0.0.1-SNAPSHOT.jar /tmp/kstream.jar > /dev/null 2>&1
rm -rf /tmp/kstream-master/
ls -la /tmp/