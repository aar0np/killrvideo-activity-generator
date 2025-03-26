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

This app works from a set of videos in the `youtube_videos` table. Add entries into that table, and the app will process 25 of them during each run. There is a sample file of YouTube videos in the csv/ directory, that can be loaded using the [DataStax Bulk Loader](https://github.com/datastax/dsbulk), like this:

```sh
{$DSBULK_LOCATION}/bin/dsbulk load -url csv/sample_youtube_videos.csv -k killrvideo -t youtube_videos
```

The csv/ directory also has a file of random comments, which are used during comment generation.

## Running

```sh
mvn spring-boot:run
```
