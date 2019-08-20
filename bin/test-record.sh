#!/usr/bin/env bash

docker exec -it docker_kafka_1 bash /opt/kafka_2.11-1.1.1/bin/kafka-console-producer.sh --broker-list localhost:29092 --topic topic-test:1:1 <<EOF
{"key":"test-key", "value":"val" , "timeStamp":"1566313128308"}
EOF
