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
Application uses basic authentication

## Example cURL requests

Delete Dish by id
```
curl -X DELETE "http://localhost:8080/admin/dishes/1" -H "accept: application/json" -H "authorization: Basic YWRtaW46cGFzc3dvcmQ="
```

Create Dish
```
curl -X POST "http://localhost:8080/admin/dishes/1" -H "accept: application/json" -H "authorization: Basic YWRtaW46cGFzc3dvcmQ=" -H "Content-Type: application/json" -d "{ \"price\": 15.4, \"title\": \"Very tasty dish\"}"
```

Update Dish
```
curl -X POST "http://localhost:8080/admin/dishes/1" -H "accept: application/json" -H "authorization: Basic YWRtaW46cGFzc3dvcmQ=" -H "Content-Type: application/json" -d "{ \"title\": \"My favorite dish!\", \"price\": 60, \"id\": 5}"
```

Create Restaurant
```
curl -X POST "http://localhost:8080/admin/restaurants" -H "accept: application/json" -H "authorization: Basic YWRtaW46cGFzc3dvcmQ=" -H "Content-Type: application/json" -d "{ \"title\": \"McDonald's\"}"
```

Update Restaurant
```
curl -X POST "http://localhost:8080/admin/restaurants" -H "accept: application/json" -H "authorization: Basic YWRtaW46cGFzc3dvcmQ=" -H "Content-Type: application/json" -d "{ \"title\": \"New Restaurant\", \"id\": 1}"
```

Get list of all restaurants
```
curl -X GET "http://localhost:8080/restaurants" -H "accept: application/json" -H "authorization: Basic dXNlcjE6cGFzc3dvcmQ="
```

Get menu for today of restaurant by id
```
curl -X GET "http://localhost:8080/restaurants/1/menu" -H "accept: application/json" -H "authorization: Basic dXNlcjE6cGFzc3dvcmQ="
```

Get menu for some day of restaurant by id
```
curl -X GET "http://localhost:8080/restaurants/1/menu/2020-01-17" -H "accept: application/json" -H "authorization: Basic dXNlcjE6cGFzc3dvcmQ="
```

Get results of voting for today
```
curl -X GET "http://localhost:8080/votes" -H "accept: application/json" -H "authorization: Basic dXNlcjE6cGFzc3dvcmQ="
```

Vote for restaurant by id
```
curl -X POST "http://localhost:8080/votes/1" -H "accept: application/json" -H "authorization: Basic dXNlcjE6cGFzc3dvcmQ="
```
