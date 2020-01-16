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
After launching application you can find [Swagger API documentation](http://localhost:8080/swagger-ui.html)

## H2 Console
Console of embedded DB H2 available on the following page: [H2 Console](http://localhost:8080/h2-console)

## Users info
There are two types of users: with role `USER` and with role `ADMIN`. 
Here is a list of users with their roles:
user1, user2, user3, user4, user5: `ROLE_USER`
admin: `ROLE_ADMIN`
admin_and_user: `ROLE_ADMIN` and `ROLE_USER`
Password from all users: `password`

## Authorization and Authentication
To authenticate in a system do `POST` request to `/authenticate`, request body should contains `username` and `password`.
Curl example of authentication request:
```
curl -X POST "http://localhost:8080/authenticate" -H "accept: application/json" -H "Content-Type: application/json" -H "X-XSRF-TOKEN: 6d0213e9-4766-42c0-9c17-39aed77e1a33" -d "{ \"password\": \"password\", \"username\": \"admin_and_user\"}"
```
After it, if it is successful, you will recieve responce with jwt token. This jwt token should be used for authorization. Add header to your request: `Authorization: Bearer <token>`
