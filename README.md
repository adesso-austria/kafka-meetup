# Don't repeat yourself

## Exactly once processing with Apache Kafka

This is the public github-Repositoy for my Meetup-presentation hosted by adesso Austria.
Author: Dominik Dorfstetter <dominik.dorfstetter@adesso.at>

## Structure & how to start

This repository conatins:

* /dashboard
* /turbine
* /enrich
* /api
* /docker

On how to start/build the services regard to the README.md file in each subdirectory.

## Subfolder Overview

### Dashboard

Angular 9 based Dashboard that displays data on turbines from our windparks.

Port: *4200*

### Turbine

Java-based CLI tool that generates sensory data & publishes it to the **tubine-raw** topic.

### Enrichment Microservice

Spring Boot application that consumes data from **turbine-raw**, lables it & publishes the data to
the **turbine-agg** topic.

Port: *10000*

### API & Load Microservice

Spring Boot application that consumes data from the **turbine-agg** topic and loads it to a postrges-database.
Implements a REST-endpoint */turbines* from which data can be requested.

Port: *10001*

### Docker

Docker-compose is used to start up the environment. This includes:

* Kafka
* Zookeeper
* Postgres
