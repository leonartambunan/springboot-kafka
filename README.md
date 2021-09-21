# Springboot as Consumer to Kafka

## Environment

### Create docker-compose.yaml
```version: '2'
   services:
     magic:
       image: "digitsy/kafka-magic"
       ports:
         - "8989:80"
       volumes:
         - .:/config
       environment:
         KMAGIC_ALLOW_TOPIC_DELETE: "true"
         KMAGIC_CONFIG_STORE_TYPE: "file"
         KMAGIC_CONFIG_STORE_CONNECTION: "Data Source=/config/KafkaMagicConfig.db;"
         KMAGIC_CONFIG_ENCRYPTION_KEY: "ENTER_YOUR_KEY_HERE"
   
     zookeeper:
       image: confluentinc/cp-zookeeper:latest
       environment:
         ZOOKEEPER_CLIENT_PORT: 2181
         ZOOKEEPER_TICK_TIME: 2000
       ports:
         - 22181:2181
     
     kafka:
       image: confluentinc/cp-kafka:latest
       depends_on:
         - zookeeper
       ports:
         - 29092:29092
       environment:
         KAFKA_BROKER_ID: 1
         KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
         KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:29092
         KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
         KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
         KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
```

### Build the dockers

```docker-compose up -d```

### Run the SpringBoot as consumer to Kafka

```mvn spring-boot:run```


### Simulate a producer from kafka_kafka_1 docker

```kafka-console-producer --broker-list 127.0.0.1:9092 --topic my-topic```
