# PollerSystem
Building a REST Service with Spring Boot and MySql using Thymeleaf for UI.

A simple service poller that keeps a list of
services (defined by a URL), and periodically performs a HTTP GET request
to each and stores a record of the response ("OK" or "FAIL").

## Version Info

| Name        | Version           | 
| ------------- |:-------------:| 
| Java          | 11     | 
| Spring        | 2.6.3          |  
| JUnit         | 5               |

## Service Instructions

### Dependencies
You will need a mySQL Database up and running to run this application.
You can use the following commands to run mySQL in the background in docker.

```
docker compose up -d
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

## Project Structure
* **Standard Java coding structure is used for the sample app**

* Java code is located in the [`src.main.java`](src/main/java/com/kry) directory
    * Controller is in under the controllers folder:
        - [`PollerController.java`](src/main/java/com/kry/controllers/PollerController.java)
    * Service class is in under the services folder:
        - [`PollerService.java`](src/main/java/com/kry/services/PollerService.java)
    * Model class is in the models folder:
        - [`Poller.java`](src/main/java/com/kry/models/Poller.java)
    * HttpClient call and response Status enum are implemented in the clients folder:
        - [`PollerCall.java`](src/main/java/com/kry/clients/PollerCall.java)
        - [`Status.java`](src/main/java/com/kry/clients/Status.java)
    * Exception is under exceptions folder:
        - [`PollerException.java`](src/main/java/com/kry/exceptions/PollerException.java)
    * Views are in the /resources/templates folder:
       - [`index.html`](src/main/resources/templates/index.html)
       - [`pollers.html`](src/main/resources/templates/pollers.html)
       - [`add-poller.html`](src/main/resources/templates/add-poller.html)
       - [`update-poller.html`](src/main/resources/templates/update-poller.html)

* Property files are located in the [`src.main.resources`](src/main/resources) directory
* JUnit test files are located in the [`src.test.java`](src/test/java/com/kry) directory

### Functionalities covered in this project

#### Basic requirements 
  * A user needs to be able to add a new service with URL and a name
  * Added services have to be kept when the server is restarted
  * Display the name, url, creation time and status for each service
  * Provide a README in english with instructions on how to run the
  application

#### Extra requirements
  * Full create/update/delete functionality for services
  * The results from the poller are automatically shown to the user (no
need to reload the page to see results)
