# KillrVideo Activity Generator

This is a Java Spring Boot application that generates activity for the KillrVideo schema (see [killrvideo.cql](killrvideo.cql)).

### Requirements

 - Java 21 or greater
 - Apache Maven

## Building

```sh
mvn clean install
```

## Configuration

In the [application.properties](src/main/resources/application.properties) file, set the following:

```sh
spring.cassandra.local-datacenter=
spring.cassandra.keyspace-name=killrvideo
spring.cassandra.contact-points=
spring.cassandra.port=
spring.cassandra.username=
spring.cassandra.password=
spring.cassandra.connection.connect-timeout=10s
```

## Running

```sh
mvn spring-boot:run
```
