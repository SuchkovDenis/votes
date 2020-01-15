# Restaurant vote system
Repo of graduation task for TopJava online project

## Requirements
- java 11 or higher
- maven

## Build and run project
To start test run `mvn test` in terminal or command line

To build project run `mvn clean package`

After build you can run project: `java -jar target/votesystem-1.0.jar`

Application will run on port 8080 (http) without context path

## API documentation
After launching application you can find [Swagger API documentation](localhost:8080/swagger-ui.html)

## H2 Console
Console of embedded DB H2 available on the following page: [H2 Console](localhost:8080/h2-console)
