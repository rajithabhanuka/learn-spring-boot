# learn-spring-boot
This spring boot tutorial to beginners and mid-level techies.
According to every topic I will create a branch, please pull relevant branch according to that.
Master Branch has all the topics which we cover in the future.

Please Note, I don't consider unit testing here, so I skip the test for every time

### Part 1 - Create Project Structure ###

branch name: project-structure-part-1
Maven Command: ```mvn clean install -DskipTests=true```

### Part 2 - Understand HTTP Methods ###

branch name: http-methods-part-2
Maven Command: ```mvn clean install -DskipTests=true```

#### post ####
```
curl --location --request POST 'http://localhost:8080/fruits' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id" : 1,
    "name" : "Berry",
    "country" : "USA",
    "qty" : 100
}'
```

#### get ####
```
curl --location --request GET 'http://localhost:8080/fruits'
```

```
curl --location --request GET 'http://localhost:8080/fruits/2'
```

```
curl --location --request GET 'http://localhost:8080/fruits/by-name?name=Berry'
```


#### put ####
```
curl --location --request PUT 'http://localhost:8080/fruits/1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id" : 1,
    "name" : "Cherry",
    "country" : "Singapore",
    "qty" : 1010
}'
```

#### delete ####
```
curl --location --request DELETE 'http://localhost:8080/fruits/2'
```

### Part 3 - Validations ###

### Part 4 - Service Layer ###