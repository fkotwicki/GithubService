# README #
Simple rest service which returns details of given Github repository.

### Usage ###
GET /repositories/{owner}/{repository-name}

### Run ###
Tests:
./gradlew check

Application:
./gradlew bootRun 
or 
./gradlew build && java -jar build/libs/GithubService-0.0.1-SNAPSHOT.jar

### Issues ###
Currently limited to 60 requests per hour (https://developer.github.com/v3/#rate-limiting).

### To do ###
* Production ready (spring-actuator, dockerfile)
* Requests limit (maybe cache mechanism?)

