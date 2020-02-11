# API Microservice

Consumes data from the Kafka topic **turbine-agg** and loads it into a postgres database.

Also implements a REST-endpoint **/turbines** where we can GET our data.

Port: *9001*

## How to start

Inside */api*:

```bash
mvn clean install && mvn sping-boot:run
```
