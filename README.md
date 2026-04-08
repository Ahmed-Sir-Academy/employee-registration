# employee-registration

1. Controller (Endpoint)
2. Schedular (Cron Job) - **Job** - Every 15 mins i want a run a Job (Insert an employee for every 15 mins)
3. Event Based Messages (Kafka) - 

# Kafka Commands:

## Start the Server:
```shell
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

## Create the topic:
```shell
.\bin\windows\kafka-topics.bat --create --topic my-first-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

## Create a producer
```shell
.\bin\windows\kafka-console-producer.bat --topic my-first-topic --bootstrap-server localhost:9092
```

## Create a Consumer
```shell
.\bin\windows\kafka-console-consumer.bat --topic my-first-topic --from-beginning --bootstrap-server localhost:9092
```
