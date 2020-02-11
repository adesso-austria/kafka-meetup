# Enrichment Microservice

Consumes data from the Kafka topic **turbine-raw** and labels it.

After that it produces the data to **turbines-agg**.

Utilizes **Guava** as a Bloom-filter for deduplication.

Port: *9000*

## How to start

Inside */enrich*:

```bash
mvn clean install && mvn sping-boot:run
```
