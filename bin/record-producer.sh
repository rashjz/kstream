#!/bin/bash

docker exec -i docker_kafka_1 /bin/sh /opt/kafka_2.11-1.1.1/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topic-test-srt << EOF
{"key":"test-key", "value":"val" , "timeStamp":"1566313128308"}
EOF

echo " value sent"