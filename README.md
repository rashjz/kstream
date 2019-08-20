# kstream
###### This kafka stream application was build for training purpose 
###### for to run application use docker-compose

Docker Compose images list :
 1) kafka 
 2) zookeeper  
 3) kstream - kafka stream application 
 4) grafana 
 
Testing:  bin/record-produces.sh to send json to topic
```bash
docker exec -it docker_kafka_1 bash /opt/kafka_2.11-1.1.1/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topic-test-srt 
{"key":"test-key", "value":"val" , "timeStamp":"1566313128308"}

```