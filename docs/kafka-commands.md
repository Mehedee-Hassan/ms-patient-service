


## Producer
```declarative

$ docker ps
$ docker exec -it kafka /bin/bash
$ opt/bitnami/kafka/bin/kafka-console-producer.sh  --bootstrap-server localhost:9092 --topic test-topic
$ >test
$ >

```

## Consumer
```declarative

$ docker ps
$ docker exec -it kafka /bin/bash
$ /opt/bitnami/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning
test
```
