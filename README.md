# PollerSystem
Building a REST Service with Spring Boot and MySql using Thymeleaf for UI

## Version Info

| Name        | Version           | 
| ------------- |:-------------:| 
| Java          | 11     | 
| Spring        | 2.6.3          |  
| JUnit         | 5               |

## Service Instructions

### Dependencies
You will need a mySQL Database up and running to run this application.
You can use the following commands to run mySQL in docker.

```
docker compose up
```

### Building

To build and run this service locally you can use the following commands:

```
mvn clean package

java -jar target/pollerSystem-1.0-SNAPSHOT.jar
```

or

```
mvn spring-boot:run
```

### Testing

To run the local tests use the command below:

```
mvn clean test
```
### Application link

[Go to our local service] (http://localhost:8080/index)


