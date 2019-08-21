#!/bin/sh

apk add --update maven
apk add --update  unzip
wget -P /tmp/ 'https://github.com/rashjz/kstream/archive/master.zip' && \
unzip /tmp/master.zip -d /tmp/
mvn package -f /tmp/kstream-master/pom.xml
ls -la /tmp/kstream-master/